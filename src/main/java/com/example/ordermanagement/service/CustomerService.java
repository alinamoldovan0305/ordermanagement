package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    public boolean existsById;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // --------------------- GET ALL ---------------------
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    // --------------------- GET BY ID ---------------------
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    // --------------------- CREATE / UPDATE ---------------------
    public Customer save(Customer customer) {
        // Optional: validări custom, ex: email unic
        if (customer.getEmail() != null && customerRepository.existsById(customer.getId())){
            throw new IllegalArgumentException("Email already exists!");
        }
        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer updatedCustomer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());

        return customerRepository.save(existing);
    }

    // --------------------- DELETE ---------------------
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
