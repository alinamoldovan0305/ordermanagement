package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.repository.ContractLineRepository;

import java.util.List;

public class ContractLineService{
    private ContractLineRepository contractLineRepository = new ContractLineRepository();

    public void saveContractLine(ContractLine contractLine){
        contractLineRepository.save(contractLine);
    }

    public List<ContractLine> getAllContractLines(){
        return contractLineRepository.findAll();
    }

    public ContractLine getContractLine(String id){
        return contractLineRepository.findById(id);
    }

    public void deleteContractLine(String id){
        contractLineRepository.delete(id);
    }

    public void updateContractLine(String id, ContractLine updatedContractLine){
        contractLineRepository.update(id, updatedContractLine);
    }
}