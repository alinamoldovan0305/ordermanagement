package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contracttypes")
public class ContractTypeController extends GenericController<ContractType> {

    public ContractTypeController(ContractTypeService service) {
        super(service, "contracttype");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contracttype", new ContractType());
        return "contracttype/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractType contractType = service.getById(id);
        if (contractType == null) {
            return "redirect:/contracttypes";
        }
        model.addAttribute("contracttype", contractType);
        return "contracttype/form";
    }

    @PostMapping("/{id}/edit")
    public String updateContract(@PathVariable String id, ContractType contractType) {
        contractType.setId(id);
        service.update(id, contractType);
        return "redirect:/contracttypes";
    }
}
