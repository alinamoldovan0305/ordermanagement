package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService extends GenericService<Product> {
    public ProductService(ProductRepository repository) {
        super(repository);
    }

    public List<Product> getProductsInStock() {
        return getAll().stream()
                .filter(Product::isInStock) // doar produsele cu stoc > 0
                .collect(Collectors.toList());
    }
}

