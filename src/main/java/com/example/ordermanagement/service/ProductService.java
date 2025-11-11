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


    @Override
    public List<Product> getAll() {
        // Afișează doar produsele care au stoc > 0
        return super.getAll()
                .stream()
                .filter(product -> product.getStock() > 0)
                .collect(Collectors.toList());
    }
}
