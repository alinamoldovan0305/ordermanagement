package com.example.ordermanagement.service;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ContractRepository contractRepository;


    public CustomerService(CustomerRepository customerRepository,  ContractRepository contractRepository) {
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }


    public Customer save(Customer customer) {

        validateCustomer(customer, true); // "true" = create

        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer updatedCustomer) {

        Customer existing = customerRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Customer not found with id: " + id));

        updatedCustomer.setId(id);

        validateCustomer(updatedCustomer, false); // "false" = update

        existing.setName(updatedCustomer.getName());
        existing.setCurrency(updatedCustomer.getCurrency());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhonenumber(updatedCustomer.getPhonenumber());

        return customerRepository.save(existing);
    }
//
//    public void delete(Long id) {
//
//        Customer customer = customerRepository.findById(id)
//                .orElseThrow(() ->
//                        new EntityNotFoundException("Customer not found with id: " + id));
//
//        if (!customer.getOrders().isEmpty()) {
//            throw new IllegalStateException("Cannot delete this customer because they have existing orders.");
//        }
//
//        if (!customer.getContracts().isEmpty()) {
//            throw new IllegalStateException("Cannot delete this customer because they have existing contracts.");
//        }
//
//        customerRepository.deleteById(id);
//    }

    @Transactional
    public void delete(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        // Cannot delete if orders exist
        if (!customer.getOrders().isEmpty()) {
            throw new IllegalStateException("Cannot delete this customer because they have existing orders.");
        }

        // 1️⃣ Delete all inactive contracts
        contractRepository.deleteByCustomerIdAndStatus(id, ContractStatus.DOWN);

        // 2️⃣ If any active contracts remain, stop deletion
        boolean hasActiveContracts =
                contractRepository.existsByCustomerIdAndStatus(id, ContractStatus.ACTIVE);

        if (hasActiveContracts) {
            throw new IllegalStateException("Cannot delete this customer because they have active contracts.");
        }

        // 3️⃣ Delete the customer
        customerRepository.deleteById(id);
    }


    private void validateCustomer(Customer customer, boolean isCreate) {


        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is mandatory.");
        }

        if (customer.getCurrency() == null || customer.getCurrency().trim().isEmpty()) {
            throw new IllegalArgumentException("Currency is mandatory.");
        }

        if (customer.getEmail() != null && !customer.getEmail().trim().isEmpty()) {

            String email = customer.getEmail().trim();

            if (isCreate) {
                if (customerRepository.existsByEmail(email)) {
                    throw new IllegalArgumentException("Email already exists.");
                }
            } else {
                if (customerRepository.existsByEmailAndIdNot(email, customer.getId())) {
                    throw new IllegalArgumentException("This email is used by another customer.");
                }
            }
        }

        if (customer.getPhonenumber() != null &&
                customer.getPhonenumber().length() > 20) {
            throw new IllegalArgumentException("Phone number cannot exceed 20 characters.");
        }
    }

    public long getActiveContractCount(Long customerId) {
        return contractRepository.countByCustomerIdAndStatus(customerId, ContractStatus.ACTIVE);
    }

}

