package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.repository.OrderLineRepository;
import java.util.List;

public class OrderLineService {

    private OrderLineRepository orderLineRepository = new OrderLineRepository();

    public void saveOrderLine(OrderLine orderLine) {
        orderLineRepository.save(orderLine);
    }

    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }

    public OrderLine getOrderLineById(String id) {
        return orderLineRepository.findById(id);
    }

    public void deleteOrderLine(String id) {
        orderLineRepository.delete(id);
    }

//    public void updateOrderLine(String id, OrderLine updatedOrderLine) {
//        orderLineRepository.update(id, updatedOrderLine);
//    }
}

