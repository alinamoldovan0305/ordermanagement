//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.SellableItem;
//import com.example.ordermanagement.repository.SellableItemRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SellableItemService extends GenericService<SellableItem> {
//    public SellableItemService(SellableItemRepository repository) {
//        super(repository);
//    }
//}
//
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
                .orElseThrow(() -> new EntityNotFoundException("SellableItem not found with id: " + id));
    }

    public SellableItem save(SellableItem item) {
        return repository.save(item);
    }

    public SellableItem update(Long id, SellableItem updatedItem) {
        SellableItem existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SellableItem not found with id: " + id));

        existing.setName(updatedItem.getName());

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("SellableItem not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

