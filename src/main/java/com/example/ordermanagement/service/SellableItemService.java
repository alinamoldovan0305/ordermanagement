package com.example.ordermanagement.service;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.repository.SellableItemRepository;
import java.util.List;

public class SellableItemService {

    private SellableItemRepository sellableItemRepository = new SellableItemRepository();

    public void saveSellableItem(SellableItem sellableItem) {
        sellableItemRepository.save(sellableItem);
    }

    public List<SellableItem> getAllSellableItems() {
        return sellableItemRepository.findAll();
    }

    public SellableItem getSellableItemById(String id) {
        return sellableItemRepository.findById(id);
    }

    public void deleteSellableItem(String id) {
        sellableItemRepository.delete(id);
    }

    public void updateSellableItem(String id, SellableItem updatedSellableItem) {
        sellableItemRepository.update(id, updatedSellableItem);
    }
}

