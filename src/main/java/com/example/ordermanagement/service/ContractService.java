package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.repository.ContractRepository;
import java.util.List;

public class ContractService {

    private ContractRepository contractRepository = new ContractRepository();

    public void saveContract(Contract contract) {
        contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(String id) {
        return contractRepository.findById(id);
    }

    public void deleteContract(String id) {
        contractRepository.delete(id);
    }

//    public void updateContract(String id, Contract updatedContract) {
//        contractRepository.update(id, updatedContract);
//    }
}
