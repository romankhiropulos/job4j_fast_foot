package ru.job4j.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories
@EnableMongoRepositories
@SpringBootApplication
public class Job4jFastFootPaymentApp {

    public static void main(String[] args) {
        SpringApplication.run(Job4jFastFootPaymentApp.class, args);
    }
}
