package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.service.OrderLineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping
    public List<OrderLine> getOrderLines() {
        return orderLineService.getAllOrderLines();
    }

    @GetMapping
    public OrderLine getOrderLineById(@RequestParam String id) {
        return orderLineService.getOrderLineById(id);
    }

    @PostMapping
    public void createOrderLine(@RequestBody OrderLine orderLine) {
        orderLineService.saveOrderLine(orderLine);
    }

    @DeleteMapping
    public void deleteOrderLine(@RequestParam String id) {
        orderLineService.deleteOrderLine(id);
    }
}
