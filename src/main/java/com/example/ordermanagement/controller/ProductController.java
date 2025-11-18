package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends GenericController<Product> {

    private final ProductService productService;

    public ProductController(ProductService service) {
        super(service, "product");
        this.productService = service;
    }

    @Override
    @GetMapping
    public String listAll(Model model) {
        // Obține doar produsele în stoc și limitează la 3
        List<Product> products = productService.getProductsInStock()
                .stream()
                .collect(Collectors.toList());

        model.addAttribute("products", products);
        return "product/index";
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = productService.getById(id);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        return "product/form";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
        product.setId(id); // asigură corect ID-ul
        productService.update(id, product);
        return "redirect:/products";
    }
}

