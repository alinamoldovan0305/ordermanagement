package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.repository.ContractLineRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractLineService extends GenericService<ContractLine> {
    public ContractLineService(ContractLineRepository repository) {
        super(repository);
    }

}
