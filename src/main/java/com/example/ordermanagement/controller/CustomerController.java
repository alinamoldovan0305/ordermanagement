package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody Customer customer) {
        customerService.add(customer.getId(), customer);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Customer customer) {
        customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        customerService.delete(id);
    }
}
