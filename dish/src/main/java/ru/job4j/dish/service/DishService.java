package ru.job4j.dish.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.dish.repository.DishRepositoryMemory;
import ru.job4j.domain.model.Category;
import ru.job4j.domain.model.Dish;
import ru.job4j.domain.model.Type;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService implements IDishService {

    private final DishRepositoryMemory dishRepositoryMemory;

    public Collection<Dish> findAll() {
        return dishRepositoryMemory.findAll();
    }

    @Override
    public void addDish(Dish dish) {
        dishRepositoryMemory.save(dish);
    }

    @Override
    public void removeAll() {
        dishRepositoryMemory.removeAll();
    }

    @Override
    public void removeById(Long id) {
        dishRepositoryMemory.removeById(id);
    }

    @Override
    public void update(Dish dish) {
        dishRepositoryMemory.save(dish);
    }

    @Override
    public void updateCostById(Long id, Float newCost) {
        Optional<Dish> dish = dishRepositoryMemory.findById(id);
        dish.ifPresent(val -> {
            val.setCost(newCost);
            dishRepositoryMemory.save(val);
        });
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepositoryMemory.findById(id);
    }

    @Override
    public Collection<Dish> findAllByCategory(Category category) {
        return dishRepositoryMemory.findAllByCategory(category);
    }

    @Override
    public Collection<Dish> findAllByType(Type type) {
        return dishRepositoryMemory.findAllByType(type);
    }

    @Override
    public Collection<Dish> findAllByTypeAndCost(Type type, Float cost) {
        return dishRepositoryMemory.findAllByTypeAndCost(type, cost);
    }
}
