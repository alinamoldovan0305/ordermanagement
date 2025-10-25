package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Customer;
import java.util.*;

public class CustomerRepository {

    private Map<String, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public Customer findById(String id) {
        return customers.get(id);
    }

    public void delete(String id) {
        customers.remove(id);
    }
}
