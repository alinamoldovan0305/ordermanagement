package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.service.ContractTypeService;
import com.example.ordermanagement.model.ContractLine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ContractTypeController {

    public final ContractTypeService contractTypeService;

    public ContractTypeController(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public List<ContractType> getContractTypes() {
        return contractTypeService.getAllContractTypes();
    }

    @GetMapping
    public ContractType getContractTypeById(@RequestParam String id) {
        return contractTypeService.getContractTypeById(id);
    }

    @PostMapping
    public void createContractType(@RequestBody ContractType contractType) {
        contractTypeService.saveContractType(contractType);
    }

    @DeleteMapping
    public void deleteContractType(@RequestParam String id) {
        contractTypeService.deleteContractType(id);
    }
}
