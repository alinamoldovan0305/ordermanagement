package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InFileRepository<Order> {
    public OrderRepository() {
        super("src/main/resources/data/order.json",  Order.class);
    }
}


