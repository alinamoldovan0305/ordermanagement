package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.service.ContractTypeService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        model.addAttribute(
                "types",
                service.filterAndSort(name, sortBy, direction)
        );

        // preserve form values
        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

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
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Acest tip de contract a fost sters cu succes."
            );

        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Acest tip de contract nu poate fi sters pentru ca este utilizat de unul sau mai multe contracte."
            );
        }

        return "redirect:/contract-types";
    }

    // ---------- DETAILS ----------
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {

        ContractType type = service.getById(id);

        if (type == null) {
            return "redirect:/contract-types";
        }

        model.addAttribute("contractType", type);
        return "contracttype/details";
    }

}
