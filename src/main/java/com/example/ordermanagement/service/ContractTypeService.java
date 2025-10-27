package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.repository.ContractTypeRepository;
import java.util.List;

public class ContractTypeService {

    private ContractTypeRepository contractTypeRepository = new ContractTypeRepository();

    public void saveContractType(ContractType contractType) {
        contractTypeRepository.save(contractType);
    }

    public List<ContractType> getAllContractTypes() {
        return contractTypeRepository.findAll();
    }

    public ContractType getContractTypeById(String id) {
        return contractTypeRepository.findById(id);
    }

    public void deleteContractType(String id) {
        contractTypeRepository.delete(id);
    }

    public void updateContractType(String id, ContractType updatedContractType) {
        contractTypeRepository.update(id, updatedContractType);
    }
}
