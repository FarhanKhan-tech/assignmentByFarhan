package com.tanmay.assignment.contrpller;

import com.tanmay.assignment.entity.Order;
import com.tanmay.assignment.entity.OrderItem;
import com.tanmay.assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestParam Long userId) {
        return orderService.createOrder(userId);
    }

    @PostMapping("/{id}/items")
    public OrderItem addItemToOrder(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return orderService.addItemToOrder(id, orderItem);
    }

    @GetMapping("/{id}/total")
    public BigDecimal getTotalOrderPrice(@PathVariable Long id) {
        return orderService.getTotalOrderPrice(id);
    }
}
