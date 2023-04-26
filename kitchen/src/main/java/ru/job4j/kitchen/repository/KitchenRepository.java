package ru.job4j.kitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.model.KitchenJob;

@Repository
public interface KitchenRepository extends JpaRepository<KitchenJob, Long> {
}
