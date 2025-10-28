package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.repository.CustomerRepository;
import java.util.List;

public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomer(String id) {
        customerRepository.delete(id);
    }

//    public void  updateCustomer(String id, Customer updatedCustomer) {
//        customerRepository.update(id, updatedCustomer);
//    }
}

