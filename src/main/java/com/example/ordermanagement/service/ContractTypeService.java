package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractTypeService extends GenericService<ContractType> {
    public ContractTypeService(ContractTypeRepository repository) {
        super(repository);
    }
}