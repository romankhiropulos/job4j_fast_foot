package ru.job4j.order.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.repository.OrderRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderStatus checkStatus(Long orderId) {
        return Objects.requireNonNull(orderRepository.findById(orderId).get()).getOrderStatus();
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order entity) {
        return orderRepository.save(entity);
    }
}
