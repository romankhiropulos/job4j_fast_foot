package ru.job4j.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
@EntityScan(basePackages = {"ru.job4j.domain"})
public class Job4jFastFootOrderApp {

    @KafkaListener(topics = "msg")
    public void msgListener(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(Job4jFastFootOrderApp.class, args);
    }
}
