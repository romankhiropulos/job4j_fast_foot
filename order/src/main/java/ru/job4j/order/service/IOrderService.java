package ru.job4j.order.service;

import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;

import java.util.Optional;

public interface IOrderService extends IService<Order> {

    Optional<OrderStatus> findStatusById(Long orderId);
}
