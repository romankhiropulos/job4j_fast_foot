package ru.job4j.domain.model;

import lombok.Data;

@Data
public class Order {

    private Long id;

    private Customer customer;

    private String description;

    private OrderStatus orderStatus;
}
