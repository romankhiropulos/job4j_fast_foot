package ru.job4j.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("account")
public class Account {

    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String region;
    private String subregion;
    private Integer age;
    private Double money;

    @DBRef
    private Country country;
}



