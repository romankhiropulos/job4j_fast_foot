package ru.job4j.domain.model;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(schema = "public", name = "food_order")
public class Order {

    private Long id;

    private Customer customer;

    private String description;

    private OrderStatus orderStatus;
}
