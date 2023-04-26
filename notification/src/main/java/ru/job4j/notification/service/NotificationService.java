package ru.job4j.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.domain.dto.OrderDto;
import ru.job4j.domain.model.Notification;
import ru.job4j.notification.repository.NotificationRepository;
import ru.job4j.order.service.OrderService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final OrderService orderService;

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> save(Notification notification) {
        return Optional.of(notificationRepository.save(notification));
    }

    @KafkaListener(
            id = "KafkaConsumerNotificationConfig",
            topics = "messengers",
            containerFactory = "kafkaListenerContainerNotificationFactory"
    )
    public void msgListener(ConsumerRecord<Long, OrderDto> msg) {
        Optional<OrderDto> valueOpt = Optional.of(msg.value());
        log.debug("message from notification service: " + valueOpt.get());
        valueOpt.ifPresentOrElse(
                value -> notificationRepository.save(Notification.builder()
                                .description(value.getDescription())
                                .order(orderService.findById(value.getId()).orElseThrow())
                                .build()),
                () -> {
                    throw new NoSuchElementException("Order is null!");
                }
        );
    }
}
