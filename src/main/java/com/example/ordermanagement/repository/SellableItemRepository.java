package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.SellableItem;
import org.springframework.stereotype.Repository;

@Repository
public class SellableItemRepository extends InFileRepository<SellableItem> {
    public SellableItemRepository() {
        super("src/main/resources/data/sellableItem.json",  SellableItem.class);
    }
}

