package ru.job4j.dish.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.domain.model.Category;
import ru.job4j.domain.model.Dish;
import ru.job4j.domain.model.Type;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DishRepositoryMemory {

     private final Map<Long, Dish> dishes;

     {
         dishes = new ConcurrentHashMap<>();

         Category meat = Category.builder().name("Meat").build();
         Category vegetable = Category.builder().name("Vegetables").build();
         Category fruit = Category.builder().name("Fruits").build();

         Type steak = Type.builder().category(meat).name("Стейк").build();
         Type apple = Type.builder().category(fruit).name("Яблоко").build();
         Type tomato = Type.builder().category(vegetable).name("Помидор").build();
         Type potato = Type.builder().category(vegetable).name("Картофель").build();

         dishes.put(1L, Dish.builder().id(1L).category(meat).type(steak).name("Стейк говядины").build());
         dishes.put(2L, Dish.builder().id(2L).category(fruit).type(apple).name("Апорт Алма-Атинский").build());
         dishes.put(3L, Dish.builder().id(3L).category(vegetable).type(tomato).name("Томат \"Бычье сердце\"").build());
         dishes.put(4L, Dish.builder().id(4L).category(vegetable).type(potato).name("Картофель мытый").build());
     }

     public Collection<Dish> findAll() {
         return dishes.values();
     }

    public Optional<Dish> findById(Long id) {
        return Optional.of(dishes.get(id));
    }

    public void save(Dish dish) {
         dishes.put(dish.getId(), dish);
    }

    public void removeAll() {
         dishes.clear();
    }

    public void removeById(Long id) {
         dishes.remove(id);
    }

    public Collection<Dish> findAllByCategory(Category category) {
         return dishes.values().stream().filter(d -> category.equals(d.getCategory())).toList();
    }

    public Collection<Dish> findAllByType(Type type) {
        return dishes.values().stream().filter(d -> type.equals(d.getType())).toList();
    }

    public Collection<Dish> findAllByTypeAndCost(Type type, Float cost) {
         return dishes.values().stream().filter(d -> type.equals(d.getType()) && cost.equals(d.getCost())).toList();
    }
}
