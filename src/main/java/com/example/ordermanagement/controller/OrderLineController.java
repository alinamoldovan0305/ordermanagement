package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.service.OrderLineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderlines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping
    public List<OrderLine> getAll() {
        return orderLineService.getAll();
    }

    @GetMapping("/{id}")
    public OrderLine getById(@PathVariable String id) {
        return orderLineService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody OrderLine orderLine) {
        orderLineService.add(orderLine.getId(), orderLine);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody OrderLine orderLine) {
        orderLineService.update(id, orderLine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        orderLineService.delete(id);
    }
}

