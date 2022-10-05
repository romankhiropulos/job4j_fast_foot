package ru.job4j.domain.model;

import lombok.Data;

@Data
public class Dish {

    private Long id;

    private String name;

    private Float cost;

    private Category category;

    private Type type;
}
