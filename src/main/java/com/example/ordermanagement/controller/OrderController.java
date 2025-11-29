//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Order;
//import com.example.ordermanagement.service.OrderService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/orders")
//public class OrderController extends GenericController<Order> {
//
//    public OrderController(OrderService service) {
//        super(service, "order");
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("order", new Order());
//        return "order/form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable String id, Model model) {
//        Order order = service.getById(id);
//        if (order == null) {
//            return "redirect:/orders";
//        }
//        model.addAttribute("order", order);
//        return "order/form";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateOrder(@PathVariable String id, @ModelAttribute Order order) {
//        order.setId(id); // asigurăm ID corect
//        service.update(id, order);
//        return "redirect:/orders";
//    }
//    @GetMapping("/{id}")
//    public String details(@PathVariable String id, Model model) {
//        model.addAttribute("orderline", service.getById(id));
//        return "orderline/details";  //
//    }
//}
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", service.getAll());
        return "order/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/form";
    }

    @PostMapping("/new")
    public String createOrder(@ModelAttribute Order order) {
        service.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = service.getById(id);
        if (order == null) return "redirect:/orders";
        model.addAttribute("order", order);
        return "order/form";
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        service.update(id, order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Order order = service.getById(id);
        if (order == null) return "redirect:/orders";
        model.addAttribute("order", order);
        return "order/details";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/orders";
    }
}
