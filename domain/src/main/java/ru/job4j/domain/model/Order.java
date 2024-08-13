package ru.job4j.domain.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "food_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish_id")
    private Long dishId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String description;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;
}
