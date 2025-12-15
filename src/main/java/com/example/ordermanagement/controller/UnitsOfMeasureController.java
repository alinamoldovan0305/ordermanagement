package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/units")
public class UnitsOfMeasureController {

    private final UnitsOfMeasureService service;

    public UnitsOfMeasureController(UnitsOfMeasureService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String symbol,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        model.addAttribute(
                "units",
                service.filterAndSort(name, symbol, sortBy, direction)
        );

        model.addAttribute("name", name);
        model.addAttribute("symbol", symbol);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "units/index";
    }


    // ---------- DETAILS ----------
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {

        UnitsOfMeasure unit = service.getById(id);
        model.addAttribute("unit", unit);

        return "units/details";
    }


    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("unit", new UnitsOfMeasure());
        return "units/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("unit") UnitsOfMeasure unit,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "units/form";
        }

        service.save(unit);
        return "redirect:/units";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        UnitsOfMeasure unit = service.getById(id);
        model.addAttribute("unit", unit);

        return "units/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("unit") UnitsOfMeasure unit,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "units/form";
        }

        service.update(id, unit);
        return "redirect:/units";
    }

    // ---------- DELETE ----------

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Unitatea de măsură a fost ștearsă cu succes."
            );

        } catch (IllegalStateException | IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    ex.getMessage()
            );
        }

        return "redirect:/units";
    }

}
