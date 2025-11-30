package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final CustomerRepository customerRepo;
    private final ContractRepository contractRepo;

    public OrderController(OrderService service,
                           CustomerRepository customerRepo,
                           ContractRepository contractRepo) {
        this.service = service;
        this.customerRepo = customerRepo;
        this.contractRepo = contractRepo;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", service.getAll());
        return "order/index";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("contracts", contractRepo.findAll());
        return "order/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("order") Order order,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("contracts", contractRepo.findAll());
            return "order/form";
        }

        service.save(order);
        return "redirect:/orders";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Order order = service.getById(id);

        model.addAttribute("order", order);
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("contracts", contractRepo.findAll());

        return "order/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("order") Order order,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("contracts", contractRepo.findAll());
            return "order/form";
        }

        service.update(id, order);
        return "redirect:/orders";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/orders";
    }
}
