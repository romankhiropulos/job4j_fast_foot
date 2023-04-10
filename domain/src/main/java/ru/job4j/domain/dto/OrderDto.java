package ru.job4j.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

    private Long id;

    private CustomerDto customer;

    private String description;

    private OrderStatusDto orderStatus;

    @Override
    public String toString() {
        return "OrderDto{"
                + "id=" + id
                + ", customer=" + customer
                + ", description='" + description + '\''
                + ", orderStatus=" + orderStatus
                + '}';
    }
}
