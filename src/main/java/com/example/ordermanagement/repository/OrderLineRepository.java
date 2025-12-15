//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.OrderLine;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderLineRepository extends InFileRepository<OrderLine> {
//    public OrderLineRepository() {
//        super("src/main/resources/data/orderLine.json",  OrderLine.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
