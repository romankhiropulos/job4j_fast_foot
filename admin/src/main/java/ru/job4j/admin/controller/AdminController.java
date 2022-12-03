package ru.job4j.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.admin.service.DishService;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final DishService dishService;

    @GetMapping("/dishes")
    public String posts(Model model) {
        model.addAttribute("dishes", dishService.findAll());
        return "dishes";
    }
}
