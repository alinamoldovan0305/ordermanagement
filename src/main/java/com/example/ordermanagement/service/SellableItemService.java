package com.example.ordermanagement.service;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.repository.SellableItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SellableItemService extends GenericService<SellableItem> {
    public SellableItemService(SellableItemRepository repository) {
        super(repository);
    }
}

