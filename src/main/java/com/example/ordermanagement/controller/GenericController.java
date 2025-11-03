package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.CustomerService;
import com.example.ordermanagement.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public abstract class GenericController<T> {

    protected final GenericService<T> service;
    protected final String entityName;

    protected GenericController(CustomerService service, String entityName) {
        this.service = (GenericService<T>) service;
        this.entityName = entityName;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute(entityName + "s", service.getAll());
        return entityName + "/index";
    }

    // Lasă fiecare subclass să definească showCreateForm() pentru entitatea sa
    public abstract String showCreateForm(Model model);

    @PostMapping
    public String createEntity(@ModelAttribute T entity) {
        String id = String.valueOf(System.currentTimeMillis());
        service.add(id, entity);
        return "redirect:/" + entityName + "s-view";
    }

    @PostMapping("/{id}/delete")
    public String deleteEntity(@PathVariable String id) {
        service.delete(id);
        return "redirect:/" + entityName + "s-view";
    }
}
