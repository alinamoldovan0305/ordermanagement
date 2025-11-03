package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.repository.ContractRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends GenericService<Contract> {
    public ContractService(ContractRepository repository) {
        super(repository);
    }
}