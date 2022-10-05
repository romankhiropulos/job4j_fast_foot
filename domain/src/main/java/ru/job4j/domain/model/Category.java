package ru.job4j.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Category {

    private String name;

    private List<Type> types;
}
