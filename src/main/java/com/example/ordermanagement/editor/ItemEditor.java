package com.example.ordermanagement.editor;

import java.beans.PropertyEditorSupport;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.service.SellableItemService;

public class ItemEditor extends PropertyEditorSupport {

    private final SellableItemService sellableItemService;

    public ItemEditor(SellableItemService sellableItemService) {
        this.sellableItemService = sellableItemService;
    }

    @Override
    public void setAsText(String id) {
        if (id == null || id.isEmpty()) {
            setValue(null);
        } else {
            SellableItem item = sellableItemService.getById(Long.parseLong(id));
            setValue(item);
        }
    }
}
