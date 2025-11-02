package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping
    public Order getOrderById(@RequestParam String id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @DeleteMapping
    public void deleteOrderById(@RequestParam String id) {
        orderService.deleteOrder(id);
    }
}
