package ru.job4j.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.model.Order;
import ru.job4j.order.service.IOrderService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        validateOrder(order);
        return new ResponseEntity<Order>(
                this.orderService.save(order),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Order order) {
        validateOrder(order);
        this.orderService.save(order);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modify(@PathVariable long id, @RequestBody Map<Object, Object> orderFields) {
        Order entityToPatch = orderService.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Orders is not found. Please, check request."
                )
        );
        patch(entityToPatch, orderFields);
        validateOrder(entityToPatch);
        this.orderService.save(entityToPatch);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Order order = new Order();
        order.setId(id);
        this.orderService.delete(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = (List<Order>) this.orderService.findAll();
        return new ResponseEntity<List<Order>>(
                orders.isEmpty() ? List.of(new Order()) : orders,
                orders.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable long id) {
        var order = this.orderService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order is not found. Please, check request."
        ));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    private void validateOrder(Order order) throws NullPointerException {
        String errMsg = "Order must not be empty";
        String text = order.getDescription();
        Objects.requireNonNull(text, errMsg);
        text = text.strip();
        if (Objects.equals(text, "")) {
            throw new NullPointerException(errMsg);
        }
    }

    private void patch(Order entityToPatch, Map<Object, Object> orderFields) {
        for (Map.Entry<Object, Object> entry : orderFields.entrySet()) {
            Field field = ReflectionUtils.findField(Order.class, (String) entry.getKey());
            if (field != null) {
                field.setAccessible(true);
                switch (field.getType().getTypeName()) {
                    case ("int"):
                        ReflectionUtils.setField(field, entityToPatch, Integer.valueOf((String) entry.getValue()));
                        break;
                    case ("long"):
                        ReflectionUtils.setField(field, entityToPatch, Long.valueOf((String) entry.getValue()));
                        break;
                    default:
                        Class<?> theClass = null;
                        try {
                            theClass = Class.forName(field.getType().getTypeName());
                        } catch (ClassNotFoundException e) {
                            throw new IllegalArgumentException("Type cast exception!");
                        }
                        Object obj = Objects.requireNonNull(theClass).cast(entry.getValue());
                        ReflectionUtils.setField(field, entityToPatch, obj);
                        break;
                }
            }
        }
    }
}

