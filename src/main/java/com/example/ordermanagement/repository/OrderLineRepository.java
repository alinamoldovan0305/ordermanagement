
package com.example.ordermanagement.repository;
import org.springframework.data.domain.Sort;
import java.util.List;

import com.example.ordermanagement.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    // filter by order name
    List<OrderLine> findByOrder_NameContainingIgnoreCase(
            String orderName,
            Sort sort
    );

    // filter by item (product/service) name
    List<OrderLine> findByItem_NameContainingIgnoreCase(
            String itemName,
            Sort sort
    );

    // filter by unit name
    List<OrderLine> findByUnit_NameContainingIgnoreCase(
            String unitName,
            Sort sort
    );

    // filter by ALL (order + item + unit)
    List<OrderLine> findByOrder_NameContainingIgnoreCaseAndItem_NameContainingIgnoreCaseAndUnit_NameContainingIgnoreCase(
            String orderName,
            String itemName,
            String unitName,
            Sort sort
    );

}
