package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public List<Contract> getContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{id}")
    public Contract getContractsById(@PathVariable String id) {
        return contractService.getContractById(id);
    }

    @PostMapping
    public void create(@RequestBody Contract contract) {
        contractService.saveContract(contract);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        contractService.deleteContract(id);
    }


    /*
    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Contract contract) {
        contractService.updateContract(id, contract);
    }
    */
}
