//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Product;
//import com.example.ordermanagement.model.SellableItem;
//import com.example.ordermanagement.service.SellableItemService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ui.Model;
//
//@Controller
//@RequestMapping("/sellableitems")
//public class SellableItemController extends GenericController<SellableItem> {
//
//    public SellableItemController(SellableItemService service) {
//        super(service, "sellableitem");
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("sellableitem", new Product());
//        return "sellableitem/form";
//    }
//}
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.service.SellableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sellableitems")
public class SellableItemController {

    private final SellableItemService service;

    public SellableItemController(SellableItemService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        List<SellableItem> items = service.getAll();
        model.addAttribute("sellableitems", items);
        return "sellableitem/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sellableitem", new Product()); // default form pentru Product
        return "sellableitem/form";
    }

    @PostMapping("/new")
    public String createItem(@ModelAttribute SellableItem item) {
        service.save(item);
        return "redirect:/sellableitems";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        SellableItem item = service.getById(id);
        if (item == null) return "redirect:/sellableitems";
        model.addAttribute("sellableitem", item);
        return "sellableitem/form";
    }

    @PostMapping("/{id}/edit")
    public String updateItem(@PathVariable Long id, @ModelAttribute SellableItem item) {
        service.update(id, item);
        return "redirect:/sellableitems";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        SellableItem item = service.getById(id);
        if (item == null) return "redirect:/sellableitems";
        model.addAttribute("sellableitem", item);
        return "sellableitem/details";
    }

    @GetMapping("/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/sellableitems";
    }
}
