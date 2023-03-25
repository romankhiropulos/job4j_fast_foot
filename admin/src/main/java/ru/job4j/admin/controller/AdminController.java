package ru.job4j.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.job4j.admin.Job4jFastFootAdminApp;
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

    @GetMapping("/restartportget/{port}")
    public void restartport(@PathVariable String port) {
        Job4jFastFootAdminApp.restart(port);
    }

    @GetMapping("/restartget")
    public void restartget() {
        Job4jFastFootAdminApp.restart("dummy");
    }
}
