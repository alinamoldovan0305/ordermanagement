package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.repository.ProductRepository;
import java.util.List;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(String id) {
        productRepository.delete(id);
    }

//    public void updateProduct(String id, Product updatedProduct) {
//        productRepository.update(id, updatedProduct);
//    }
}
