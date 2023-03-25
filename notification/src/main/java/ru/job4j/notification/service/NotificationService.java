package ru.job4j.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Notification;
import ru.job4j.notification.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> save(Notification notification) {
        return Optional.of(notificationRepository.save(notification));
    }
}
