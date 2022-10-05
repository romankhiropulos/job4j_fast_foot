package ru.job4j.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Type {

    private String name;

    private Category category;

    private List<Dish> dishes;
}
