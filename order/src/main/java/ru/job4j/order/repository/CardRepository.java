package ru.job4j.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.model.Card;

@Repository
public interface CardRepository  extends JpaRepository<Card, Long> {
}
