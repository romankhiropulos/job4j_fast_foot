package ru.job4j.domain.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
