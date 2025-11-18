package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("customers", service.getAll());
        return "customer/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @PostMapping
    public String createCustomer(Customer customer) {
        String id = customer.getId();
        if (id == null || id.isBlank()) {
            id = String.valueOf(System.currentTimeMillis());
            customer.setId(id);
        }
        service.add(id, customer);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        service.delete(id);
        return "redirect:/customers";
    }

    // =====================================
    // EDITARE CUSTOMER
    // =====================================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Customer customer = service.getById(id);
        if (customer == null) {
            return "redirect:/customers"; // dacă nu există, redirect la listă
        }
        model.addAttribute("customer", customer);
        return "customer/form";
    }

    @PostMapping("/{id}/edit")
    public String updateCustomer(@PathVariable String id, Customer customer) {
        customer.setId(id); // asigurăm că ID-ul nu se schimbă
        service.update(id, customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable String id, Model model) {
        Customer customer = service.getById(id);
        if (customer == null) {
            return "redirect:/customers"; // dacă nu există, redirect la listă
        }
        model.addAttribute("customer", customer);
        return "customer/details";
    }

}

