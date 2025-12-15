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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping
    public String list(
            @RequestParam(required = false) String orderName,
            @RequestParam(required = false) String itemName,
            @RequestParam(required = false) String unitName,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        model.addAttribute(
                "lines",
                service.filterAndSort(orderName, itemName, unitName, sortBy, direction)
        );

        model.addAttribute("orderName", orderName);
        model.addAttribute("itemName", itemName);
        model.addAttribute("unitName", unitName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "orderline/index";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "orderline/form";
    }


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


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        OrderLine line = service.getById(id);

        if (line.getOrder().isDelivered()) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Nu poți edita linii pentru o comandă livrată."
            );
            return "redirect:/order-lines";
        }

        model.addAttribute("orderLine", line);
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "orderline/form";
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("orderLine") OrderLine line,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("orders", orderRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "orderline/form";
        }

        try {
            service.update(id, line);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Linia de comandă a fost actualizată cu succes."
            );
            return "redirect:/order-lines";

        } catch (IllegalStateException | IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("orders", orderRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "orderline/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Linia de comandă a fost ștearsă cu succes."
            );
        } catch (IllegalStateException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    ex.getMessage()
            );
        }

        return "redirect:/order-lines";
    }
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("orderLine", service.getById(id));
        return "orderline/details";
    }
}

