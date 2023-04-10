package ru.job4j.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@ComponentScan(basePackages = {"ru", "ru.job4j", "ru.job4j.order"})
@EnableAutoConfiguration
//@EntityScan(basePackages = "ru.job4j.domain")
@EnableJpaRepositories
public class Job4jFastFootNotificationApp {

    public static void main(String[] args) {
        SpringApplication.run(Job4jFastFootNotificationApp.class, args);
    }
}
