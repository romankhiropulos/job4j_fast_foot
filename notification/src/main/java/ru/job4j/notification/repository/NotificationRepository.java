package ru.job4j.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.domain.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
