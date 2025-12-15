package com.example.ordermanagement.controller;
import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractTypeRepository;
import com.example.ordermanagement.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
        return "contracts/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String createContract(@Valid @ModelAttribute("contract") Contract contract,
                                 BindingResult bindingResult,
                                 Model model) {

        bindCustomer(contract, bindingResult);
        bindContractType(contract, bindingResult);
        bindStatus(contract, bindingResult);

        if (bindingResult.hasErrors()) {
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
        return "contracts/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String updateContract(@PathVariable Long id,
                                 @Valid @ModelAttribute("contract") Contract contract,
                                 BindingResult bindingResult,
                                 Model model) {

        bindCustomer(contract, bindingResult);
        bindContractType(contract, bindingResult);
        bindStatus(contract, bindingResult);

        if (bindingResult.hasErrors()) {
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


    // ==========================================================
    //       BINDING HELPERS (customer, type, status)
    // ==========================================================

    private void bindCustomer(Contract contract, BindingResult bindingResult) {
        if (contract.getCustomer() == null || contract.getCustomer().getName() == null) {
            bindingResult.rejectValue("customer", "customer.required", "Customer name is required");
            return;
        }

        String inputName = contract.getCustomer().getName().trim();

        Customer customer = customerRepo.findByName(inputName)
                .orElse(null);

        if (customer == null) {
            bindingResult.rejectValue("customer", "customer.notfound",
                    "Customer does not exist. Please create the customer first.");
            return;
        }
        contract.setCustomer(customer);

    }


    private void bindContractType(Contract contract, BindingResult bindingResult) {
        if (contract.getContractType() == null || contract.getContractType().getName() == null) {
            bindingResult.rejectValue("contractType", "contractType.required", "Contract type is required");
            return;
        }

        String inputName = contract.getContractType().getName().trim();

        ContractType contractType = contractTypeRepo.findByName(inputName)
                .orElseGet(() -> {
                    ContractType newType = new ContractType();
                    newType.setName(inputName);
                    return contractTypeRepo.save(newType);
                });

        contract.setContractType(contractType);
    }


    private void bindStatus(Contract contract, BindingResult bindingResult) {
        if (contract.getStatus() == null) {
            bindingResult.rejectValue("status", "status.required", "Status is required");
            return;
        }

        try {
            String value = contract.getStatus().name();
            ContractStatus parsed = ContractStatus.valueOf(value.toUpperCase());
            contract.setStatus(parsed);

        } catch (Exception e) {
            bindingResult.rejectValue(
                    "status",
                    "status.invalid",
                    "Status must be one of: " + Arrays.toString(ContractStatus.values())
            );
        }
    }
}
