package com.example.ordermanagement.controller;

import com.example.ordermanagement.enums.ServiceStatus;
import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.service.ServiceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ServiceStatus status,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        model.addAttribute(
                "services",
                service.filterAndSort(name, status, sortBy, direction)
        );

        model.addAttribute("name", name);
        model.addAttribute("status", status);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        model.addAttribute("statuses", ServiceStatus.values());

        return "service/index";
    }


    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("serviceEntity", new ServiceEntity());
        model.addAttribute("statuses", ServiceStatus.values());
        return "service/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("serviceEntity") ServiceEntity serviceEntity,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ServiceStatus.values());
            return "service/form";
        }

        service.save(serviceEntity);
        return "redirect:/services";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        ServiceEntity serviceEntity = service.getById(id);

        model.addAttribute("serviceEntity", serviceEntity);
        model.addAttribute("statuses", ServiceStatus.values());

        return "service/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("serviceEntity") ServiceEntity serviceEntity,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ServiceStatus.values());
            return "service/form";
        }

        service.update(id, serviceEntity);
        return "redirect:/services";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Serviciul a fost șters cu succes."
            );

        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Serviciul nu poate fi șters deoarece este utilizat în comenzi."
            );

        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    ex.getMessage()
            );
        }

        return "redirect:/services";
    }


    // ---------- DETAILS ----------
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ServiceEntity serviceEntity = service.getById(id);
        model.addAttribute("serviceEntity", serviceEntity);
        return "service/details";
    }

}
