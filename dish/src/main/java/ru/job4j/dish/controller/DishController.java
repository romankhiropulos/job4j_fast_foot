package ru.job4j.dish.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dish.service.DishService;
import ru.job4j.domain.model.Dish;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/getAll")
    public Collection<Dish> getAll() {
        return dishService.findAll();
    }
}
