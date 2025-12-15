package com.example.ordermanagement.service;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ContractRepository contractRepository;

    private final OrderRepository orderRepository;


    public CustomerService(CustomerRepository customerRepository,  ContractRepository contractRepository,  OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
        this.orderRepository = orderRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit clientul cu id: " + id));
    }


    public Customer save(Customer customer) {

        validateCustomer(customer, true); // "true" = create

        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer updatedCustomer) {

        Customer existing = customerRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Nu s-a gasit clientul cu id: " + id));

        updatedCustomer.setId(id);

        validateCustomer(updatedCustomer, false); // "false" = update

        existing.setName(updatedCustomer.getName());
        existing.setCurrency(updatedCustomer.getCurrency());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhonenumber(updatedCustomer.getPhonenumber());

        return customerRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit clientul cu id: " + id));

        if (!customer.getOrders().isEmpty()) {
            throw new IllegalStateException("Acest client nu poate fi sters pentru ca are comenzi active.");
        }

        contractRepository.deleteByCustomerIdAndStatus(id, ContractStatus.DOWN);

        boolean hasActiveContracts =
                contractRepository.existsByCustomerIdAndStatus(id, ContractStatus.ACTIVE);

        if (hasActiveContracts) {
            throw new IllegalStateException("Acest client nu poate fi sters pentru ca are contracte active.");
        }

        customerRepository.deleteById(id);
    }


    private void validateCustomer(Customer customer, boolean isCreate) {

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele clientului este obligatoriu.");
        }

        // Currency is optional → remove validation

        // Email is mandatory
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email-ul este obligatoriu.");
        }

        String email = customer.getEmail().trim();
        if (isCreate) {
            if (customerRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("Email-ul exista deja.");
            }
        } else {
            if (customerRepository.existsByEmailAndIdNot(email, customer.getId())) {
                throw new IllegalArgumentException("Acest email este folosit de un alt client.");
            }
        }

        // Phone mandatory
        if (customer.getPhonenumber() == null || customer.getPhonenumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Numarul de telefon este obligatoriu.");
        }

        if (customer.getPhonenumber().length() > 20) {
            throw new IllegalArgumentException("Numarul de telefon nu poate depasi 20 de caractere.");
        }
    }


    public long getActiveContractCount(Long customerId) {
        return contractRepository.countByCustomerIdAndStatus(customerId, ContractStatus.ACTIVE);

    }

    public long getOrderCount(Long customerId) {
        return orderRepository.countByCustomerId(customerId);
    }

    public List<Contract> getContractsByCustomerId(Long customerId) {
        return contractRepository.findByCustomerId(customerId);
    }


}

