package ru.job4j.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
