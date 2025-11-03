package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.service.SellableItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellableitems")
public class SellableItemController {

    private final SellableItemService sellableItemService;

    public SellableItemController(SellableItemService sellableItemService) {
        this.sellableItemService = sellableItemService;
    }

    @GetMapping
    public List<SellableItem> getAll() {
        return sellableItemService.getAll();
    }

    @GetMapping("/{id}")
    public SellableItem getById(@PathVariable String id) {
        return sellableItemService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody SellableItem sellableItem) {
        sellableItemService.add(sellableItem.getId(), sellableItem);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody SellableItem sellableItem) {
        sellableItemService.update(id, sellableItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        sellableItemService.delete(id);
    }
}
