package ru.job4j.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "food_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String description;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
}
