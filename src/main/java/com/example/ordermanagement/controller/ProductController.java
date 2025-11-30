package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.getAll());
        return "product/index";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("product") Product product,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/form";
        }

        service.save(product);
        return "redirect:/products";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Product product = service.getById(id);
        model.addAttribute("product", product);

        return "product/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("product") Product product,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/form";
        }

        service.update(id, product);
        return "redirect:/products";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}
