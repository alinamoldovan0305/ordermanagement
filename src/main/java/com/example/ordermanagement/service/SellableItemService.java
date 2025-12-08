
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.repository.SellableItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellableItemService {

    private final SellableItemRepository repository;

    public SellableItemService(SellableItemRepository repository) {
        this.repository = repository;
    }


    public List<SellableItem> getAll() {
        return repository.findAll();
    }

    public SellableItem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("SellableItem not found with id: " + id));
    }


    public SellableItem save(SellableItem item) {

        validateItem(item, true);

        return repository.save(item);
    }

    public SellableItem update(Long id, SellableItem updatedItem) {

        SellableItem existing = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("SellableItem not found with id: " + id));

        updatedItem.setId(id);

        validateItem(updatedItem, false);

        existing.setName(updatedItem.getName());

        return repository.save(existing);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("SellableItem not found with id: " + id);
        }


        repository.deleteById(id);
    }


    private void validateItem(SellableItem item, boolean isCreate) {

        if (item.getName() == null || item.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Item name is required.");
        }

        String trimmedName = item.getName().trim();

        if (isCreate) {

            if (repository.existsByNameIgnoreCase(trimmedName)) {
                throw new IllegalArgumentException("An item with this name already exists.");
            }

        } else {

            if (repository.existsByNameIgnoreCaseAndIdNot(trimmedName, item.getId())) {
                throw new IllegalArgumentException("Another item with this name already exists.");
            }
        }
    }
}


