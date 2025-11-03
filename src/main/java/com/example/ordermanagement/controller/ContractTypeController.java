package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.service.ContractTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracttypes")
public class ContractTypeController {

    private final ContractTypeService contractTypeService;

    public ContractTypeController(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public List<ContractType> getAll() {
        return contractTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ContractType getById(@PathVariable String id) {
        return contractTypeService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody ContractType contractType) {
        contractTypeService.add(contractType.getId(), contractType);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody ContractType contractType) {
        contractTypeService.update(id, contractType);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        contractTypeService.delete(id);
    }
}
