package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ServiceEntity;
import com.example.ordermanagement.repository.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceService extends GenericService<ServiceEntity> {

    public ServiceService(ServiceRepository repository) {
        super(repository);
    }
}
