package ru.job4j.order.service.mapper;

import org.mapstruct.Mapper;
import ru.job4j.domain.dto.CustomerDto;
import ru.job4j.domain.dto.OrderDto;
import ru.job4j.domain.dto.OrderStatusDto;
import ru.job4j.domain.model.Customer;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order entity);

    Order toEntity(OrderDto dto);

    CustomerDto toDto(Customer entity);

    Customer toEntity(CustomerDto dto);

    OrderStatusDto toDto(OrderStatus entity);

    OrderStatus toEntity(OrderStatusDto dto);
}
