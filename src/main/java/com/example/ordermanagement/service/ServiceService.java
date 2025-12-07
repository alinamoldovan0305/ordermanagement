
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
                .orElseThrow(() ->
                        new EntityNotFoundException("Service not found with id: " + id));
    }

    public ServiceEntity save(ServiceEntity serviceEntity) {

        validateService(serviceEntity, true);

        return repository.save(serviceEntity);
    }

    public ServiceEntity update(Long id, ServiceEntity updatedService) {

        ServiceEntity existing = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Service not found with id: " + id));

        updatedService.setId(id);

        validateService(updatedService, false);

        existing.setName(updatedService.getName());
        existing.setStatus(updatedService.getStatus());

        return repository.save(existing);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Service not found with id: " + id);
        }

        // OPTIONAL: nu ștergi dacă e folosit în order/contractLines
        // if (!existing.getContractLines().isEmpty()) { ... }

        repository.deleteById(id);
    }


    // ---------------- VALIDATOR ----------------

    private void validateService(ServiceEntity service, boolean isCreate) {

        // 1. NAME validat de SellableItem (@NotBlank + trim)
        String name = service.getName().trim();

        // 2. NAME unic
        if (isCreate) {
            if (repository.existsByNameIgnoreCase(name)) {
                throw new IllegalArgumentException("A service with this name already exists.");
            }
        } else {
            if (repository.existsByNameIgnoreCaseAndIdNot(name, service.getId())) {
                throw new IllegalArgumentException("Another service with this name already exists.");
            }
        }

        // 3. STATUS obligatoriu
        if (service.getStatus() == null) {
            throw new IllegalArgumentException("Service status is required.");
        }
    }
}
