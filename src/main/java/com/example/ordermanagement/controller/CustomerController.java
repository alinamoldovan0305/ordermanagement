package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    // Constructor injection (Spring injectează automat CustomerService)
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping
    public Customer getCustomersById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public void create(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }


//    public void update(@PathVariable String id, @RequestBody Customer customer) {
//        customerService.updateCustomer(id, customer);
//    }

    @DeleteMapping
    public void delete(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
}

