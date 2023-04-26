package ru.job4j.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.repository.OrderStatusRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public Optional<OrderStatus> findStatusById(Long orderStatusId) {
        return orderStatusRepository.findById(orderStatusId);
    }
}
