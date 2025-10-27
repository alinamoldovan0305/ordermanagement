package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.OrderLine;

import java.util.*;

public class OrderRepository {

    private Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Order findById(String id) {
        return orders.get(id);
    }

    public void delete(String id) {
        orders.remove(id);
    }

    public void update(String id, Order updatedOrder) {
        if(orders.containsKey(id)) {
            orders.put(id, updatedOrder);
        }
    }
}

