package ru.job4j.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.admin.service.DishService;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final DishService dishService;

    @GetMapping("/dishes")
    public String posts(Model model) {
        model.addAttribute("dishes", dishService.findAll());
        return "dishes";
    }
}
