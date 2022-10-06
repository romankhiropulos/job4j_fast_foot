package ru.job4j.order.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Override
    public OrderStatus checkStatus(Long orderId) {
        return null;
    }

    @Override
    public void delete(Order entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> save(Order entity) {
        return Optional.empty();
    }
}
