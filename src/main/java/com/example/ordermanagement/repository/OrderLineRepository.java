package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.OrderLine;
import java.util.*;

public class OrderLineRepository {

    private Map<String, OrderLine> orderLines = new HashMap<>();

    public void save(OrderLine orderLine) {
        orderLines.put(orderLine.getId(), orderLine);
    }

    public List<OrderLine> findAll() {
        return new ArrayList<>(orderLines.values());
    }

    public OrderLine findById(String id) {
        return orderLines.get(id);
    }

    public void delete(String id) {
        orderLines.remove(id);
    }

//    public void update(String id, OrderLine updatedOrderLine) {
//        if(orderLines.containsKey(id)) {
//            orderLines.put(id, updatedOrderLine);
//        }
//    }
}

