package ru.job4j.domain.model;

import lombok.Data;

@Data
public class Card {

    private Long id;

    private Customer customer;

    private Order order;
}
