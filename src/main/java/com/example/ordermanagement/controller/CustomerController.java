package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
//import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.service.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService =  new CustomerService();

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public String addCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "Client successfully added";
    }

    public Customer getCustomerById(String id) {
        return customerService.getCustomerById(id);
    }

    public String deleteCustomer(String id) {
        customerService.deleteCustomer(id);
        return "Customer successfully deleted";
    }

}
