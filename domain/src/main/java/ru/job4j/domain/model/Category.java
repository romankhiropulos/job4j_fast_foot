package ru.job4j.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Category {

    private String name;

    private List<Type> types;
}
