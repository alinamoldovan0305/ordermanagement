//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.OrderLine;
//import com.example.ordermanagement.service.OrderLineService;
//import com.example.ordermanagement.service.UnitsOfMeasureService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/orderlines")
//public class OrderLineController extends GenericController<OrderLine> {
//
//    private final UnitsOfMeasureService unitsOfMeasureService;
//
//    public OrderLineController(OrderLineService service, UnitsOfMeasureService unitsOfMeasureService) {
//        super(service, "orderline");
//        this.unitsOfMeasureService = unitsOfMeasureService;
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("orderline", new OrderLine());
//        model.addAttribute("units", unitsOfMeasureService.getAll()); // trimitem lista de unități
//        return "orderline/form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable String id, Model model) {
//        OrderLine orderLine = service.getById(id);
//        if (orderLine == null) {
//            return "redirect:/orderlines";
//        }
//
//        model.addAttribute("orderline", orderLine);
//        model.addAttribute("units", unitsOfMeasureService.getAll());
//
//        return "orderline/form";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateOrderLine(@PathVariable String id, @ModelAttribute OrderLine orderLine) {
//        orderLine.setId(id);
//        service.update(id, orderLine);
//        return "redirect:/orderlines";
//    }
//    @GetMapping("/{id}")
//    public String showDetails(@PathVariable String id, Model model) {
//        OrderLine ol = service.getById(id);
//
//        if (ol == null) {
//            return "redirect:/orderlines";
//        }
//
//        model.addAttribute("orderline", ol);
//        return "orderline/details";
//
//    }
//
//}
//
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.service.OrderLineService;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderlines")
public class OrderLineController {

    private final OrderLineService service;
    private final UnitsOfMeasureService unitsOfMeasureService;

    public OrderLineController(OrderLineService service, UnitsOfMeasureService unitsOfMeasureService) {
        this.service = service;
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    @GetMapping
    public String listOrderLines(Model model) {
        model.addAttribute("orderlines", service.getAll());
        return "orderline/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("orderline", new OrderLine());
        model.addAttribute("units", unitsOfMeasureService.getAll());
        return "orderline/form";
    }

    @PostMapping("/new")
    public String createOrderLine(@ModelAttribute OrderLine orderLine) {
        service.save(orderLine);
        return "redirect:/orderlines";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        OrderLine ol = service.getById(id);
        if (ol == null) return "redirect:/orderlines";
        model.addAttribute("orderline", ol);
        model.addAttribute("units", unitsOfMeasureService.getAll());
        return "orderline/form";
    }

    @PostMapping("/{id}/edit")
    public String updateOrderLine(@PathVariable Long id, @ModelAttribute OrderLine orderLine) {
        service.update(id, orderLine);
        return "redirect:/orderlines";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        OrderLine ol = service.getById(id);
        if (ol == null) return "redirect:/orderlines";
        model.addAttribute("orderline", ol);
        return "orderline/details";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrderLine(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/orderlines";
    }
}
