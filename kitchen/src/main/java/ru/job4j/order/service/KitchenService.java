package ru.job4j.order.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.domain.dto.OrderDto;

@Service
@Slf4j
public class KitchenService {

    @KafkaListener(id = "KafkaConsumerKitchenConfig", topics = "job4j_orders", containerFactory = "kafkaListenerContainerKitchenFactory")
    public void receiveOrder(ConsumerRecord<Long, OrderDto> msg) {
        OrderDto value = msg.value();
        log.debug(value.toString());
    }
}
