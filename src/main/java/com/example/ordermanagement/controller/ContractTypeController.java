package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.service.ContractTypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("types", service.getAll());
        return "contracttype/index";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractType", new ContractType());
        return "contracttype/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("contractType") ContractType type,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "contracttype/form";
        }

        service.save(type);
        return "redirect:/contract-types";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        ContractType type = service.getById(id);
        model.addAttribute("contractType", type);

        return "contracttype/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("contractType") ContractType type,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "contracttype/form";
        }

        service.update(type);

        return "redirect:/contract-types";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/contract-types";
    }
}
