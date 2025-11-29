//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.ServiceEntity;
//import com.example.ordermanagement.repository.ServiceRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ServiceService extends GenericService<ServiceEntity> {
//
//    public ServiceService(ServiceRepository repository) {
//        super(repository);
//    }
//}
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<ServiceEntity> getAll() {
        return repository.findAll();
    }

    public ServiceEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
    }

    public ServiceEntity save(ServiceEntity serviceEntity) {
        return repository.save(serviceEntity);
    }

    public ServiceEntity update(Long id, ServiceEntity updatedService) {
        ServiceEntity existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));

        existing.setName(updatedService.getName());
        existing.setStatus(updatedService.getStatus());

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Service not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
