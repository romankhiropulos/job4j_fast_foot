package ru.job4j.dish.service;

import ru.job4j.domain.model.Category;
import ru.job4j.domain.model.Type;

import java.util.Collection;
import java.util.Optional;

public interface TypeService {

    void addType(Type type);

    void removeAll();

    void removeById(Long id);

    void update(Type type);

    Optional<Type> findById(Long id);

    Optional<Type> findByName(String name);

    Collection<Type> findAllByCategory(Category category);
}
