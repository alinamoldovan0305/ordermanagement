package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products-view")
public class ProductController extends GenericController<Product> {

    public ProductController(ProductService service) {
        super(service, "product");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }
}
