package ru.job4j.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final KafkaTemplate<Long, OrderDto> kafkaTemplate;

    @Override
    public OrderStatus checkStatus(Long orderId) {
        return Objects.requireNonNull(orderRepository.findById(orderId).get()).getOrderStatus();
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

    @Override
    public Order save(Order entity) {
        Order savedOrder = orderRepository.save(entity);
        OrderDto dto = orderMapper.toDto(savedOrder);
        ListenableFuture<SendResult<Long, OrderDto>> futureMessengers = kafkaTemplate.send(
                "messengers", dto
        );
        futureMessengers.addCallback(
                x -> log.debug(String.valueOf(x)), x -> log.error(String.valueOf(x), x)
        );
        kafkaTemplate.flush();
        ListenableFuture<SendResult<Long, OrderDto>> futureOrder = kafkaTemplate.send(
                "job4j_orders", dto
        );
        futureOrder.addCallback(
                x -> log.debug(String.valueOf(x)), x -> log.error(String.valueOf(x), x)
        );
        kafkaTemplate.flush();
        return savedOrder;
    }
}
