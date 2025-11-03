package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.service.ContractLineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractlines")
public class ContractLineController {

    private final ContractLineService contractLineService;

    public ContractLineController(ContractLineService contractLineService) {
        this.contractLineService = contractLineService;
    }

    @GetMapping
    public List<ContractLine> getAll() {
        return contractLineService.getAll();
    }

    @GetMapping("/{id}")
    public ContractLine getById(@PathVariable String id) {
        return contractLineService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody ContractLine contractLine) {
        contractLineService.add(contractLine.getId(), contractLine);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody ContractLine contractLine) {
        contractLineService.update(id, contractLine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        contractLineService.delete(id);
    }
}
