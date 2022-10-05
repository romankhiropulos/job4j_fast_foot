package ru.job4j.dish.service;

import ru.job4j.domain.model.Category;

import java.util.Optional;

public interface CategoryService {

    void addCategory(Category category);

    void removeAll();

    void removeById(Long id);

    void update(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);
}
