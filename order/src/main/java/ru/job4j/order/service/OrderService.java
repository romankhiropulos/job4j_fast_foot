package ru.job4j.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.job4j.domain.dto.OrderDto;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.repository.OrderRepository;
import ru.job4j.order.service.mapper.OrderMapper;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private static final Long CREATED_ORDER_STATUS_ID = 1L;
    private static final String STATUS_NOT_FOUND = "Статус товара не найден!";
    private static final String PREORDER_QUEUE = "preorder";
    private static final String MESSENGERS_QUEUE = "messengers";
    private static final String COOKED_ORDER_LISTENED_QUEUE = "cooked_order";
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final KafkaTemplate<Long, OrderDto> kafkaTemplate;
    private final OrderStatusService orderStatusService;

    @Override
    public Order save(Order entity) {
        Optional<OrderStatus> orderStatusOptional = findStatusById(CREATED_ORDER_STATUS_ID);
        if (orderStatusOptional.isEmpty()) {
            throw new NoSuchElementException(STATUS_NOT_FOUND);
        }
        entity.setOrderStatus(orderStatusOptional.get());
        Order savedOrder = orderRepository.save(entity);
        OrderDto dto = orderMapper.toDto(savedOrder);
        ListenableFuture<SendResult<Long, OrderDto>> futureOrder = kafkaTemplate.send(
                PREORDER_QUEUE, dto
        );
        futureOrder.addCallback(
                x -> log.debug(String.valueOf(x)), x -> log.error(String.valueOf(x), x)
        );
        kafkaTemplate.flush();

        ListenableFuture<SendResult<Long, OrderDto>> futureMessengers = kafkaTemplate.send(
                MESSENGERS_QUEUE, dto
        );
        futureMessengers.addCallback(
                x -> log.debug(String.valueOf(x)), x -> log.error(String.valueOf(x), x)
        );
        kafkaTemplate.flush();
        return savedOrder;
    }

    @KafkaListener(
            id = "KafkaConsumerOrderConfig",
            topics = COOKED_ORDER_LISTENED_QUEUE,
            containerFactory = "kafkaListenerContainerOrderFactory"
    )
    public void receiveCookedOrder(ConsumerRecord<Long, OrderDto> msg) {
        OrderDto value = msg.value();
        ListenableFuture<SendResult<Long, OrderDto>> futureMessengers = kafkaTemplate.send(
                MESSENGERS_QUEUE, value
        );
        futureMessengers.addCallback(
                x -> log.debug(String.valueOf(x)), x -> log.error(String.valueOf(x), x)
        );
        kafkaTemplate.flush();
    }

    @Override
    public Optional<OrderStatus> findStatusById(Long orderStatusId) {
        return orderStatusService.findStatusById(orderStatusId);
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }
}
