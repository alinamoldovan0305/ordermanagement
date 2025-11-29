//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.Product;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ProductRepository extends InFileRepository<Product> {
//    public ProductRepository() {
//        super("src/main/resources/data/product.json", Product.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository oferă automat:
    // save(), findById(), findAll(), deleteById(), etc.

    // Poți adăuga metode custom dacă e nevoie:
    // List<Product> findByCategory(String category);
}
