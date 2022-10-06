package ru.job4j.domain.model;

import lombok.Data;

@Data
public class OrderStatus {

    private Long id;

    private Order order;
}
