package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public abstract class GenericController<T> {

    protected final GenericService<T> service;
    protected final String entityName;

    protected GenericController(GenericService<T> service, String entityName) {
        this.service = service;
        this.entityName = entityName;
    }
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute(entityName + "s", service.getAll());
        return entityName + "/index";
    }

    public abstract String showCreateForm(Model model);

//    @PostMapping
//    public String createEntity(@ModelAttribute T entity) {
//        String id = String.valueOf(System.currentTimeMillis());
//        service.add(id, entity);
//        return "redirect:/" + entityName + "s"; //
//    }

@PostMapping
public String createEntity(@ModelAttribute T entity) {
    String id = null;

    try {
        // 1) încearcă să ia ID-ul din entitate
        id = (String) entity.getClass().getMethod("getId").invoke(entity);
    } catch (Exception ignored) {}

    if (id == null || id.isBlank()) {
        // 2) fallback: dacă nu există, generează unul și scrie-l în entitate
        id = String.valueOf(System.currentTimeMillis());
        try {
            entity.getClass().getMethod("setId", String.class).invoke(entity, id);
        } catch (Exception ignored) {}
    }

    // 3) salvează cu cheia = ID-ul pe care îl vezi și în tabel
    service.add(id, entity);

    return "redirect:/" + entityName + "s";
}


    @PostMapping("/{id}/delete")
    public String deleteEntity(@PathVariable String id) {
        service.delete(id);
        return "redirect:/" + entityName + "s"; //
    }
}
