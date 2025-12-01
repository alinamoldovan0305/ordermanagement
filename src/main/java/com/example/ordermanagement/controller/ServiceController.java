package com.example.ordermanagement.controller;

import com.example.ordermanagement.enums.ServiceStatus;
import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", service.getAll());
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
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/services";
    }
}
