package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contracts")
public class ContractController extends GenericController<Contract> {

    public ContractController(ContractService service) {
        super(service, "contract");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contract", new Contract());
        return "contract/form";
    }
}

