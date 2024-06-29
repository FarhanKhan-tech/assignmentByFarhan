package com.tanmay.assignment.service;

import com.tanmay.assignment.entity.Order;
import com.tanmay.assignment.entity.OrderItem;
import com.tanmay.assignment.jpa.OrderItemRepository;
import com.tanmay.assignment.jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(BigDecimal.ZERO);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public OrderItem addItemToOrder(Long orderId, OrderItem orderItem) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);

            // Update total price
            List<OrderItem> items = order.getItems();
            BigDecimal totalPrice = items.stream()
                    .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);
            return orderItem;
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public BigDecimal getTotalOrderPrice(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return orderOptional.get().getTotalPrice();
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}
