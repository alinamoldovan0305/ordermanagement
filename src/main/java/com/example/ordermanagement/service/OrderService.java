package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ContractRepository contractRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ContractRepository contractRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }


    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Order not found with id: " + id));
    }

    public Order save(Order order) {
        validateOrder(order, true);
        return orderRepository.save(order);
    }

    public Order update(Long id, Order updatedOrder) {

        Order existing = orderRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Order not found with id: " + id));

        updatedOrder.setId(id);
        validateOrder(updatedOrder, false);

        existing.setName(updatedOrder.getName());
        existing.setCustomer(updatedOrder.getCustomer());
        existing.setContract(updatedOrder.getContract());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setDelivered(updatedOrder.isDelivered());

        return orderRepository.save(existing);
    }

    public void delete(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Order not found with id: " + id));

        if (!order.getOrderLines().isEmpty()) {
            throw new IllegalStateException("Cannot delete this order because it has order lines.");
        }

        orderRepository.deleteById(id);
    }

    private void validateOrder(Order order, boolean isCreate) {

        if (order.getName() == null || order.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Order name is required.");
        }


        if (order.getCustomer() == null || order.getCustomer().getId() == null ||
                !customerRepository.existsById(order.getCustomer().getId())) {
            throw new IllegalArgumentException("Customer does not exist.");
        }

        if (order.getContract() == null || order.getContract().getId() == null ||
                !contractRepository.existsById(order.getContract().getId())) {
            throw new IllegalArgumentException("Contract does not exist.");
        }

        Contract contract = contractRepository.findById(order.getContract().getId()).orElse(null);
        if (contract != null && !contract.getCustomer().getId().equals(order.getCustomer().getId())) {
            throw new IllegalArgumentException("Selected contract does not belong to this customer.");
        }

        if (order.getOrderDate() != null &&
                order.getOrderDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Order date cannot be in the future.");
        }
    }
}
