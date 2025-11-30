package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.repository.SellableItemRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import com.example.ordermanagement.service.OrderLineService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-lines")
public class OrderLineController {

    private final OrderLineService service;
    private final OrderRepository orderRepo;
    private final SellableItemRepository itemRepo;
    private final UnitsOfMeasureRepository unitRepo;

    public OrderLineController(OrderLineService service,
                               OrderRepository orderRepo,
                               SellableItemRepository itemRepo,
                               UnitsOfMeasureRepository unitRepo) {
        this.service = service;
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.unitRepo = unitRepo;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("lines", service.getAll());
        return "orderline/index";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "orderline/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("orderLine") OrderLine line,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("orders", orderRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "orderline/form";
        }

        service.save(line);
        return "redirect:/order-lines";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        OrderLine line = service.getById(id);

        model.addAttribute("orderLine", line);
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());

        return "orderline/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("orderLine") OrderLine line,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("orders", orderRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "orderline/form";
        }

        service.update(id, line);
        return "redirect:/order-lines";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/order-lines";
    }
}
