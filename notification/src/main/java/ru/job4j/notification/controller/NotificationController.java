package ru.job4j.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.model.Notification;
import ru.job4j.notification.service.NotificationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/getAll")
    public List<Notification> getAll() {
        return notificationService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Optional<Notification> newNotification = this.notificationService.save(notification);
        return new ResponseEntity<Notification>(
                newNotification.isEmpty() ? null : newNotification.get(),
                newNotification.isEmpty() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED
        );
    }
}
