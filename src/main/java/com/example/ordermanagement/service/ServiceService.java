package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Service;
import com.example.ordermanagement.repository.ServiceRepository;
import java.util.List;

public class ServiceService {

    private ServiceRepository serviceRepository = new ServiceRepository();

    public void saveService(Service service) {
        serviceRepository.save(service);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(String id) {
        return serviceRepository.findById(id);
    }

    public void deleteService(String id) {
        serviceRepository.delete(id);
    }

//    public void updateService(String id, Service updatedService) {
//        serviceRepository.update(id, updatedService);
//    }
}
