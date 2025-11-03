package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceEntity> getAll() {
        return serviceService.getAll();
    }

    @GetMapping("/{id}")
    public ServiceEntity getById(@PathVariable String id) {
        return serviceService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody ServiceEntity serviceEntity) {
        serviceService.add(serviceEntity.getId(), serviceEntity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody ServiceEntity serviceEntity) {
        serviceService.update(id, serviceEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        serviceService.delete(id);
    }
}
