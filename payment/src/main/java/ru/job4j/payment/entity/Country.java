package ru.job4j.payment.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Document("country")
public class Country {

    @Id
    private String id;

    private String name;

    public Country(String name) {
        this.name = name;
    }
}
