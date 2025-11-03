package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.service.SellableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class SellableItemController {

    private final SellableItemService sellableItemService;

    public SellableItemController(SellableItemService sellableItemService) {
        this.sellableItemService = sellableItemService;
    }

    @GetMapping
    public List<SellableItem> getSellableItems() {
        return sellableItemService.getAllSellableItems();
    }

    @GetMapping
    public SellableItem getSellableItemById(@RequestParam String id) {
        return sellableItemService.getSellableItemById(id);
    }

    @PostMapping
    public void createSellableItem(@RequestBody SellableItem sellableItem) {
        sellableItemService.saveSellableItem(sellableItem);
    }

    @DeleteMapping
    public void deleteSellableItem(@RequestParam String id) {
        sellableItemService.deleteSellableItem(id);
    }
}
