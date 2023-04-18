package ru.job4j.domain.dto;

import lombok.*;

import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private Long dishId;

    private CustomerDto customer;

    private String description;

    private OrderStatusDto orderStatus;

    @Override
    public String toString() {
        return new StringJoiner(",")
                .add(" " + id)
                .add(" " + dishId)
                .add(" " + customer)
                .add(" " + description)
                .add(" " + orderStatus)
                .toString();
    }
}
