package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.service.OrderLineService;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        OrderLine orderLine = service.getById(id);
        if (orderLine == null) {
            return "redirect:/orderlines";
        }

        model.addAttribute("orderline", orderLine);
        model.addAttribute("units", unitsOfMeasureService.getAll());

        return "orderline/form";
    }

    @PostMapping("/{id}/edit")
    public String updateOrderLine(@PathVariable String id, @ModelAttribute OrderLine orderLine) {
        orderLine.setId(id);
        service.update(id, orderLine);
        return "redirect:/orderlines";
    }
}

