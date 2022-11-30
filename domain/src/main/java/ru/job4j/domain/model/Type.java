package ru.job4j.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Type {

    private String name;

    private Category category;

    private List<Dish> dishes;
}
