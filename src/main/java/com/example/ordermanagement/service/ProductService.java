//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.Product;
//import com.example.ordermanagement.repository.ProductRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ProductService extends GenericService<Product> {
//    public ProductService(ProductRepository repository) {
//        super(repository);
//    }
//
//    public List<Product> getProductsInStock() {
//        return getAll().stream()
//                .filter(Product::isInStock) // doar produsele cu stoc > 0
//                .collect(Collectors.toList());
//    }
//}

package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // --------------------- GET ALL ---------------------
    public List<Product> getAll() {
        return repository.findAll();
    }

    // --------------------- GET BY ID ---------------------
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    // --------------------- CREATE ---------------------
    public Product save(Product product) {
        return repository.save(product);
    }

    // --------------------- UPDATE ---------------------
    public Product update(Long id, Product updatedProduct) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setValue(updatedProduct.getValue());
        existing.setStock(updatedProduct.getStock());

        return repository.save(existing);
    }

    // --------------------- DELETE ---------------------
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // --------------------- CUSTOM ---------------------
    public List<Product> getProductsInStock() {
        return repository.findAll().stream()
                .filter(Product::isInStock)
                .collect(Collectors.toList());
    }
}

