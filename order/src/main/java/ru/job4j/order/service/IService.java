package ru.job4j.order.service;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {

    void delete(T entity);

    void deleteById(Long id);

    Optional<T> findById(Long id);

    Collection<T> findAll();

    Optional<T> save(T entity);
}
