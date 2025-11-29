//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.Order;
//import com.example.ordermanagement.repository.OrderRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderService extends GenericService<Order> {
//    public OrderService(OrderRepository repository) {
//        super(repository);
//    }
//}
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    public Order save(Order order) {
        validateOrderRelations(order);
        return orderRepository.save(order);
    }

    public Order update(Long id, Order updatedOrder) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        validateOrderRelations(updatedOrder);

        existing.setName(updatedOrder.getName());
        existing.setCustomer(updatedOrder.getCustomer());
        existing.setContract(updatedOrder.getContract());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setDelivered(updatedOrder.isDelivered());

        return orderRepository.save(existing);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    // --------------------- Helper Methods ---------------------
    private void validateOrderRelations(Order order) {
        if (order.getCustomer() == null || order.getCustomer().getId() == null ||
                !customerRepository.existsById(order.getCustomer().getId())) {
            throw new IllegalArgumentException("Customer does not exist!");
        }

        if (order.getContract() != null && order.getContract().getId() != null &&
                !contractRepository.existsById(order.getContract().getId())) {
            throw new IllegalArgumentException("Contract does not exist!");
        }
    }
}
