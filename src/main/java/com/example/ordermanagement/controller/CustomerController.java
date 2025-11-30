package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // --------------------- LISTA ---------------------
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "customers/index";
    }

    // --------------------- FORMULAR CREARE ---------------------
    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    // --------------------- SALVARE CREARE ---------------------
    @PostMapping
    public String createCustomer(@Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "customers/form";
        }

        try {
            customerService.save(customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "customers/form";
        }

        return "redirect:/customers";
    }

    // --------------------- DETALII ---------------------
    @GetMapping("/{id}")
    public String customerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customers/details";
    }

    // --------------------- FORMULAR EDITARE ---------------------
    @GetMapping("/{id}/edit")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customers/form";
    }

    // --------------------- SALVARE EDITARE ---------------------
    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "customers/form";
        }

        try {
            customerService.update(id, customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "customers/form";
        }

        return "redirect:/customers";
    }

    // --------------------- ȘTERGERE ---------------------
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
