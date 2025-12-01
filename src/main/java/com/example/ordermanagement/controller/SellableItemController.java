package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.SellableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class SellableItemController {

    private final SellableItemService service;

    public SellableItemController(SellableItemService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.getAll());
        return "sellableitem/index";
    }

    // ---------- DETAILS ----------
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.getById(id));
        return "sellableitem/details";
    }
}
