
package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    // ADD THESE METHODS
    List<Product> findByNameContainingIgnoreCase(String name, Sort sort);

    List<Product> findByCategoryIgnoreCase(String category, Sort sort);

    List<Product> findByStockGreaterThan(int stock, Sort sort);

    List<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
            String name,
            String category,
            Sort sort
    );

}
