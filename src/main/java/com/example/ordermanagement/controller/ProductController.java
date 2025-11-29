//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Product;
//import com.example.ordermanagement.service.ProductService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/products")
//public class ProductController extends GenericController<Product> {
//
//    private final ProductService productService;
//
//    public ProductController(ProductService service) {
//        super(service, "product");
//        this.productService = service;
//    }
//
//    @Override
//    @GetMapping
//    public String listAll(Model model) {
//        // Obține doar produsele în stoc și limitează la 3
//        List<Product> products = productService.getProductsInStock()
//                .stream()
//                .collect(Collectors.toList());
//
//        model.addAttribute("products", products);
//        return "product/index";
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "product/form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable String id, Model model) {
//        Product product = productService.getById(id);
//
//        if (product == null) {
//            return "redirect:/products";
//        }
//
//        model.addAttribute("product", product);
//        return "product/form";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
//        product.setId(id); // asigură corect ID-ul
//        productService.update(id, product);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/{id}/details")
//    public String showDetails(@PathVariable String id, Model model) {
//        Product product = productService.getById(id);
//        if (product == null) {
//            return "redirect:/products"; // dacă nu există, redirect la listă
//        }
//        model.addAttribute("product", product);
//        return "product/details";
//    }
//
//}
//
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        List<Product> products = service.getProductsInStock(); // doar produsele în stoc
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @PostMapping("/new")
    public String createProduct(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = service.getById(id);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        return "product/form";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        service.update(id, product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Product product = service.getById(id);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        return "product/details";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}
