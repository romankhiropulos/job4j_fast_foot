package ru.job4j.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}


