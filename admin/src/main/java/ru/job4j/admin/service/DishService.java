package ru.job4j.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.model.Dish;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class DishService {

    @Value("${api-url}")
    private String url;

    private final RestTemplate client;

    public Collection<Dish> findAll() {
        return getList(String.format("%s/getAll", url));
    }

    private Collection<Dish> getList(String url) {
        Collection<Dish> body = client.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<Dish>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
