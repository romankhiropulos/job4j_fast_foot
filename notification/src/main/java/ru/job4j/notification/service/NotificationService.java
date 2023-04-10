package ru.job4j.notification.service;

import lombok.RequiredArgsConstructor;
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

    @KafkaListener(topics = "messengers")
    public void msgListener(ConsumerRecord<Long, OrderDto> msg) {
        System.out.println(msg.partition());
        System.out.println(msg.key());
        System.out.println(msg.value());
        Optional<OrderDto> valueOpt = Optional.of(msg.value());
        valueOpt.ifPresentOrElse(
                value -> notificationRepository.save(Notification.builder()
                                .description(value.getDescription())
                                .order(orderService.findById(msg.key()).orElseThrow())
                                .build()),
                () -> {
                    throw new NoSuchElementException("Order is null!");
                }
        );
    }
}
