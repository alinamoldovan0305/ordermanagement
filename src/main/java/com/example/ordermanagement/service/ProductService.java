
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

    private void validateProduct(Product product, boolean isCreate) {

        // 1. NAME obligatoriu
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        String trimmedName = product.getName().trim();

        // 2. NAME unic
        if (isCreate) {
            if (repository.existsByNameIgnoreCase(trimmedName)) {
                throw new IllegalArgumentException("Product name already exists.");
            }
        } else {
            if (repository.existsByNameIgnoreCaseAndIdNot(trimmedName, product.getId())) {
                throw new IllegalArgumentException("Another product with the same name already exists.");
            }
        }

        // 3. CATEGORY obligatorie
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required.");
        }

        // 4. VALUE >= 0
        if (product.getValue() < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }

        // 5. STOCK >= 0
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
    }
}

