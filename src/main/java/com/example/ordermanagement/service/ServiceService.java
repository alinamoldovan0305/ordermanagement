
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
                        new EntityNotFoundException("Nu s-a gasit serviciul cu id: " + id));
    }

    public ServiceEntity save(ServiceEntity serviceEntity) {

        validateService(serviceEntity, true);

        return repository.save(serviceEntity);
    }

    public ServiceEntity update(Long id, ServiceEntity updatedService) {

        ServiceEntity existing = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Nu s-a gasit serviciul cu id: " + id));

        updatedService.setId(id);

        validateService(updatedService, false);

        existing.setName(updatedService.getName());
        existing.setStatus(updatedService.getStatus());

        return repository.save(existing);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Nu s-a gasit serviciul cu id: " + id);
        }

        repository.deleteById(id);
    }



    private void validateService(ServiceEntity service, boolean isCreate) {

        String name = service.getName().trim();


        if (isCreate) {
            if (repository.existsByNameIgnoreCase(name)) {
                throw new IllegalArgumentException("Exista deja un serviciu cu acest nume.");
            }
        } else {
            if (repository.existsByNameIgnoreCaseAndIdNot(name, service.getId())) {
                throw new IllegalArgumentException("Exista deja un serviciu cu acest nume.");
            }
        }

        if (service.getStatus() == null) {
            throw new IllegalArgumentException("Statusul este obligatoriu.");
        }
    }
}
