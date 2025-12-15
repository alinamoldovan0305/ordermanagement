
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.repository.ContractLineRepository;
import com.example.ordermanagement.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    private final ContractLineRepository contractLineRepository;

    public ProductService(ProductRepository repository,  ContractLineRepository contractLineRepository) {
        this.repository = repository;
        this.contractLineRepository = contractLineRepository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit produsul cu id: " + id));
    }

    public Product save(Product product) {

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele produsului este obligatoriu.");
        }

        String name = product.getName().trim();

        if (name.length() < 2) {
            throw new IllegalArgumentException("Numele produsului este prea scurt.");
        }

        if (!name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException(
                    "Numele produsului trebuie sa contina doar litere."
            );
        }

        if (name.matches("\\d+")) {
            throw new IllegalArgumentException("Numele produsului nu poate fi un numar.");
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria este obligatorie.");
        }

        if (product.getValue() <= 0) {
            throw new IllegalArgumentException("Valoarea produsului trebuie sa fie mai mare decat 0.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stocul nu poate fi negativ.");
        }

        product.setName(name);
        product.setCategory(product.getCategory().trim());

        return repository.save(product);
    }


    public Product update(Long id, Product updatedProduct) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit produsul cu id: " + id));

        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setValue(updatedProduct.getValue());
        existing.setStock(updatedProduct.getStock());

        return repository.save(existing);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Produsul nu poate fi gasit.");
        }

        if (contractLineRepository.existsByItem_Id(id)) {
            throw new IllegalStateException(
                    "Acest produs nu poate fi sters deoarece contine linii de contract."
            );
        }

        repository.deleteById(id);
    }


    public List<Product> getProductsInStock() {
        return repository.findAll().stream()
                .filter(Product::isInStock)
                .collect(Collectors.toList());
    }

    private void validateProduct(Product product, boolean isCreate) {

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele produsului este obligatoriu.");
        }

        String trimmedName = product.getName().trim();

        if (!trimmedName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException(
                    "Numele produsului trebuie sa contina doar litere."
            );
        }

        if (isCreate) {
            if (repository.existsByNameIgnoreCase(trimmedName)) {
                throw new IllegalArgumentException("Numele produsului exista deja.");
            }
        } else {
            if (repository.existsByNameIgnoreCaseAndIdNot(trimmedName, product.getId())) {
                throw new IllegalArgumentException("Exista deja un produs cu acest nume.");
            }
        }

        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria este obligatorie.");
        }

        if (product.getValue() < 0) {
            throw new IllegalArgumentException("Valoarea nu poate fi negativa.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stocul nu poate fi negativ.");
        }
    }
    public List<Product> filterAndSort(
            String name,
            String category,
            Boolean inStock,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        boolean hasName = name != null && !name.isBlank();
        boolean hasCategory = category != null && !category.isBlank();
        boolean hasStock = inStock != null && inStock;

        if (hasName && hasCategory) {
            return repository.findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
                    name, category, sort
            );
        }

        if (hasName) {
            return repository.findByNameContainingIgnoreCase(name, sort);
        }

        if (hasCategory) {
            return repository.findByCategoryIgnoreCase(category, sort);
        }

        if (hasStock) {
            return repository.findByStockGreaterThan(0, sort);
        }

        return repository.findAll(sort);
    }


}

