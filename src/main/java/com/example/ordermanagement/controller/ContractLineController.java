package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.ContractLineService;
import com.example.ordermanagement.model.ContractLine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ContractLineController {

    public final ContractLineService contractLineService;

    public ContractLineController(ContractLineService contractLineService) {
        this.contractLineService = contractLineService;
    }

    @GetMapping
    public List<ContractLine> getContractLines() {
        return contractLineService.getAllContractLines();
    }

    @GetMapping
    public ContractLine getContractLineById(@RequestParam String id) {
        return contractLineService.getContractLine(id);
    }

    @PostMapping
    public void createContractLine(@RequestBody ContractLine contractLine) {
        contractLineService.saveContractLine(contractLine);
    }

    @DeleteMapping
    public void deleteContractLine(@RequestParam String id) {
        contractLineService.deleteContractLine(id);
    }
}
