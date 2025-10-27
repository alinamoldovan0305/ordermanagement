package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.repository.OrderRepository;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(String id) {
        orderRepository.delete(id);
    }

    public void updateOrder(String id, Order updatedOrder) {
        orderRepository.update(id, updatedOrder);
    }
}

