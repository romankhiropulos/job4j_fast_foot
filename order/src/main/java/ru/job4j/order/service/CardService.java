package ru.job4j.order.service;

import ru.job4j.domain.model.Card;

import java.util.Collection;
import java.util.Optional;

public class CardService implements IService<Card> {

    @Override
    public void delete(Card entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Card> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Card> findAll() {
        return null;
    }

    @Override
    public Optional<Card> save(Card entity) {
        return Optional.empty();
    }
}
