package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Product;
import java.util.*;

public class ProductRepository {

    private Map<String, Product> products = new HashMap<>();

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(String id) {
        return products.get(id);
    }

    public void delete(String id) {
        products.remove(id);
    }

    public void update(String id, Product updatedProduct) {
        if(products.containsKey(id)) {
            products.put(id, updatedProduct);
        }
    }
}
