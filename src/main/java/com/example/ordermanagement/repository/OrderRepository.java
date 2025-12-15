
package com.example.ordermanagement.repository;
import org.springframework.data.domain.Sort;
import java.util.List;

import com.example.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    long countByCustomerId(Long customerId);
    List<Order> findByNameContainingIgnoreCase(String name, Sort sort);

    List<Order> findByDelivered(boolean delivered, Sort sort);

    List<Order> findByCustomer_NameContainingIgnoreCase(
            String customerName,
            Sort sort
    );

    List<Order> findByNameContainingIgnoreCaseAndDelivered(
            String name,
            boolean delivered,
            Sort sort
    );

}

