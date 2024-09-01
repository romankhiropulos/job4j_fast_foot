package ru.job4j.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("account")
public class Account {

    @Id
    String id;
    String lastName;
    String firstName;
    String patronymic;
    String country;
    String region;
    String subregion;
    Integer age;
    Double money;
}



