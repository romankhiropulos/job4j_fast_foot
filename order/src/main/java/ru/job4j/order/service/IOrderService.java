package ru.job4j.order.service;

import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;

public interface IOrderService extends IService<Order> {

    OrderStatus checkStatus(Long orderId);
}
