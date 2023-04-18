package ru.job4j.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.domain.dto.OrderDto;

@Service
@Slf4j
public class KitchenService {

    @KafkaListener(id = "KafkaConsumerKitchenConfig", topics = "job4j_orders")
    public void receiveOrder(OrderDto order) {
        log.debug(order.toString());
    }
}
