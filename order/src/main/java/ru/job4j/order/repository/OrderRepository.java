package ru.job4j.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
