//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.Order;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderRepository extends InFileRepository<Order> {
//    public OrderRepository() {
//        super("src/main/resources/data/order.json",  Order.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

