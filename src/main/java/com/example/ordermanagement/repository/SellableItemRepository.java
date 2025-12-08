
package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.SellableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellableItemRepository extends JpaRepository<SellableItem, Long> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

}
