package ru.job4j.kitchen.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.job4j.domain.dto.OrderDto;
import ru.job4j.domain.model.KitchenJob;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.kitchen.repository.KitchenRepository;
import ru.job4j.order.service.OrderStatusService;
import ru.job4j.order.service.mapper.OrderMapper;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KitchenService {

    private static final Integer MIN_TIME = 5;
    private static final Integer ACCEPTABLE_TIME = 15;
    private static final Integer MAX_TIME = 30;
    private static final Long COMPLETED_ORDER_STATUS_ID = 2L;
    private static final Long CANCELED_ORDER_STATUS_ID = 3L;
    private static final String COOKED_ORDER_QUEUE = "cooked_order";
    private static final String PREORDER_LISTENED_QUEUE = "preorder";

    private final OrderMapper orderMapper;
    private final KafkaTemplate<Long, OrderDto> kafkaTemplate;
    private final OrderStatusService orderStatusService;
    private final KitchenRepository kitchenRepository;

    @KafkaListener(
            id = "KafkaConsumerKitchenConfig",
            topics = PREORDER_LISTENED_QUEUE,
            containerFactory = "kafkaListenerContainerKitchenFactory"
    )
    public void receiveOrder(ConsumerRecord<Long, OrderDto> msg) {
        log.debug("The listener in the KITCHEN got to work...");
        OrderDto value = msg.value();
        Optional<OrderStatus> orderStatusOpt;
        log.debug("Order from topic \"preorder\": ".concat(value.toString()));
        orderStatusOpt = isPossibleToPrepareOrder() ? findStatusById(COMPLETED_ORDER_STATUS_ID)
                                                    : findStatusById(CANCELED_ORDER_STATUS_ID);
        if (orderStatusOpt.isPresent()) {
            value.setOrderStatus(orderMapper.toDto(orderStatusOpt.get()));
            KitchenJob kitchenJob = kitchenRepository.save(KitchenJob.builder()
                    .description(orderStatusOpt.get().getDescription())
                    .order(orderMapper.toEntity(value))
                    .build());
            log.debug(kitchenJob.toString());
            CompletableFuture<SendResult<Long, OrderDto>> futureOrder = kafkaTemplate.send(
                    COOKED_ORDER_QUEUE, value
            );
            futureOrder.whenComplete((x, throwable) -> {
                if (throwable != null) {
                    log.error("Произошла ошибка: " + throwable.getMessage());
                } else {
                    log.debug("Результат: " + x);
                }
            });
            kafkaTemplate.flush();
        } else {
            throw new NoSuchElementException("Can't find status order");
        }
        log.debug("The listener in the KITCHEN has finished work!");
    }

    private static Boolean isPossibleToPrepareOrder() {
        return new Random().nextInt(MIN_TIME, MAX_TIME) < ACCEPTABLE_TIME;
    }

    public Optional<OrderStatus> findStatusById(Long orderStatusId) {
        return orderStatusService.findStatusById(orderStatusId);
    }
}