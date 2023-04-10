package ru.job4j.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(basePackages = {"ru", "ru.job4j", "ru.job4j.order", "ru.job4j.notification"})
@EnableKafka
@EntityScan(basePackages = "ru.job4j.domain")
@EnableJpaRepositories(basePackages = {"ru.job4j"})
public class Job4jFastFootNotificationApp {

    public static void main(String[] args) {
        SpringApplication.run(Job4jFastFootNotificationApp.class, args);
    }
}
