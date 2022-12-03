package ru.job4j.domain.model;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(schema = "public", name = "card")
public class Card {

    private Long id;

    private Customer customer;

    private Order order;
}
