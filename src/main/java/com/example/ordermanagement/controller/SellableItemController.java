package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.service.SellableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/sellableitems")
public class SellableItemController extends GenericController<SellableItem> {

    public SellableItemController(SellableItemService service) {
        super(service, "sellableitem");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sellableitem", new SellableItem());
        return "sellableitem/form";
    }
}
