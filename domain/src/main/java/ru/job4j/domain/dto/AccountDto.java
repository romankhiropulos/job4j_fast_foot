package ru.job4j.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

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
