package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public List<Contract> getAll() {
        return contractService.getAll();
    }

    @GetMapping("/{id}")
    public Contract getById(@PathVariable String id) {
        return contractService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody Contract contract) {
        contractService.add(contract.getId(), contract);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Contract contract) {
        contractService.update(id, contract);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        contractService.delete(id);
    }
}
