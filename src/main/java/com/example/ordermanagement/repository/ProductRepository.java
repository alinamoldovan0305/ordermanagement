package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InFileRepository<Product> {
    public ProductRepository() {
        super("src/main/resources/data/product.json", Product.class);
    }
}
