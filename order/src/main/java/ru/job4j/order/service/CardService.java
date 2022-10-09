package ru.job4j.order.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Card;
import ru.job4j.order.repository.CardRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class CardService implements IService<Card> {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void delete(Card entity) {
        cardRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Collection<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card save(Card entity) {
        return cardRepository.save(entity);
    }
}
