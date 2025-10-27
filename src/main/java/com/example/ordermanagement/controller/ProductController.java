package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.ProductService;
import com.example.ordermanagement.model.Product;
import java.util.List;

public class ProductController {
    private final ProductService productService =  new ProductService();

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product getProductById(String id) {
        return productService.getProductById(id);
    }

    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "Product added successfully.";
    }

    public String deleteProduct(String id) {
        productService.deleteProduct(id);
        return "Product deleted successfully.";
    }
}
