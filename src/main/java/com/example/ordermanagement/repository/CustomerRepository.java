package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InFileRepository<Customer> {
    public CustomerRepository() {
        super("src/main/resources/data/customer.json",  Customer.class);
    }
}

