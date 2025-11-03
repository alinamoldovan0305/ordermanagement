package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.model.Service;
import com.example.ordermanagement.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getServices() {
        return serviceService.getAllServices();
    }

    @GetMapping
    public Service getServiceById(@RequestParam String id) {
        return serviceService.getServiceById(id);
    }

    @PostMapping
    public void createService(@RequestBody Service service) {
        serviceService.saveService(service);
    }

    @DeleteMapping
    public void deleteService(@RequestParam String id) {
        serviceService.deleteService(id);
    }
}
