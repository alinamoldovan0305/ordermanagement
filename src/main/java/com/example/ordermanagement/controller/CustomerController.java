package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends GenericController<Customer> {

    public CustomerController(CustomerService service) {
        super(service, "customer");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }
}
