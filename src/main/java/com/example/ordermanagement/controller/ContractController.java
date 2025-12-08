
package com.example.ordermanagement.controller;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractTypeRepository;
import com.example.ordermanagement.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;
    private final CustomerRepository customerRepo;
    private final ContractTypeRepository contractTypeRepo;

    public ContractController(ContractService contractService,
                              CustomerRepository customerRepo,
                              ContractTypeRepository contractTypeRepo) {
        this.contractService = contractService;
        this.customerRepo = customerRepo;
        this.contractTypeRepo = contractTypeRepo;
    }

    // ---------- LIST ----------
    @GetMapping
    public String listContracts(Model model) {
        model.addAttribute("contracts", contractService.getAll());
        return "contracts/index";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("types", contractTypeRepo.findAll());
        model.addAttribute("statuses", ContractStatus.values());
        return "contracts/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String createContract(@Valid @ModelAttribute("contract") Contract contract,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("types", contractTypeRepo.findAll());
            model.addAttribute("statuses", ContractStatus.values());
            return "contracts/form";
        }

        contractService.save(contract);
        return "redirect:/contracts";
    }


    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Contract contract = contractService.getById(id);

        model.addAttribute("contract", contract);
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("types", contractTypeRepo.findAll());
        model.addAttribute("statuses", ContractStatus.values());

        return "contracts/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String updateContract(@PathVariable Long id,
                                 @Valid @ModelAttribute("contract") Contract contract,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("types", contractTypeRepo.findAll());
            model.addAttribute("statuses", ContractStatus.values());
            return "contracts/form";
        }

        contract.setId(id);
        contractService.update(contract);

        return "redirect:/contracts";
    }

    // ---------- DETAILS ----------
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        model.addAttribute("contract", contractService.getById(id));
        return "contracts/details";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable Long id) {
        contractService.delete(id);
        return "redirect:/contracts";
    }
}

