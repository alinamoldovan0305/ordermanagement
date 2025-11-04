package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.service.OrderLineService;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderlines")
public class OrderLineController extends GenericController<OrderLine> {

    private final UnitsOfMeasureService unitsOfMeasureService;

    public OrderLineController(OrderLineService service, UnitsOfMeasureService unitsOfMeasureService) {
        super(service, "orderline");
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("orderline", new OrderLine());
        model.addAttribute("units", unitsOfMeasureService.getAll()); // trimitem lista de unități
        return "orderline/form";
    }
}

