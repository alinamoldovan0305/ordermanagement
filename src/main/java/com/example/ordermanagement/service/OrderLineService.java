package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService extends GenericService<OrderLine> {
    public OrderLineService(OrderLineRepository repository) {
        super(repository);
    }
}


