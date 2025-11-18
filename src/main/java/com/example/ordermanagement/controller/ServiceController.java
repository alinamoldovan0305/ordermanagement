package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController extends GenericController<ServiceEntity> {

    public ServiceController(ServiceService service) {
        super(service, "service");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new ServiceEntity());
        return "service/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        ServiceEntity serviceEntity = service.getById(id);

        if (serviceEntity == null) {
            return "redirect:/services";
        }

        model.addAttribute("service", serviceEntity);
        return "service/form";
    }

    @PostMapping("/{id}/edit")
    public String updateService(@PathVariable String id,
                                @ModelAttribute ServiceEntity serviceEntity) {

        serviceEntity.setId(id); // asigurăm ID-ul corect
        service.update(id, serviceEntity);
        return "redirect:/services";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable String id, Model model) {
        ServiceEntity serviceEntity = service.getById(id);
        if (serviceEntity == null) {
            return "redirect:/services"; // dacă nu există, redirect la listă
        }
        model.addAttribute("service", serviceEntity);
        return "service/details";
    }

}
