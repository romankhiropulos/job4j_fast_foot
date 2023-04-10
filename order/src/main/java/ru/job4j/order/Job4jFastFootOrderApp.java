package ru.job4j.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication(scanBasePackages = {"ru.job4j.domain", "ru.job4j.order"})
@EntityScan(basePackages = {"ru.job4j.domain"})
public class Job4jFastFootOrderApp {

    public static void main(String[] args) {
        SpringApplication.run(Job4jFastFootOrderApp.class, args);
    }
}
