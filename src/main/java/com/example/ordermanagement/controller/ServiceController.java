package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services-view")
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
}
