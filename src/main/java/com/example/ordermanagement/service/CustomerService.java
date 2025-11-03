package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends GenericService<Customer> {
    public CustomerService(CustomerRepository repository) {
        super(repository);
    }
}

