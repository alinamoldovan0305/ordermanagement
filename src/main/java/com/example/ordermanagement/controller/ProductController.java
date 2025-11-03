package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductsById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void create(@RequestBody Product product) {
        productService.saveProduct(product);
    }

//    @PutMapping("/{id}")
//    public void update(@PathVariable String id, @RequestBody Product product) {
//        // Presupunem că ProductService are o metodă updateProduct
//        productService.updateProduct(id, product);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
