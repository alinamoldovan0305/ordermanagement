package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.SellableItem;
import java.util.*;

public class SellableItemRepository {

    private Map<String, SellableItem> sellableItems = new HashMap<>();

    public void save(SellableItem sellableItem) {
        sellableItems.put(sellableItem.getId(), sellableItem);
    }

    public List<SellableItem> findAll() {
        return new ArrayList<>(sellableItems.values());
    }

    public SellableItem findById(String id) {
        return sellableItems.get(id);
    }

    public void delete(String id) {
        sellableItems.remove(id);
    }

    public void update(String id, SellableItem updatedSellableItem) {
        if(sellableItems.containsKey(id)) {
            sellableItems.put(id, updatedSellableItem);
        }
    }
}
