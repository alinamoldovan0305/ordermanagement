package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.service.ContractLineService;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contractlines")
public class ContractLineController extends GenericController<ContractLine> {

    private final UnitsOfMeasureService unitsOfMeasureService;

    public ContractLineController(ContractLineService service, UnitsOfMeasureService unitsOfMeasureService) {
        super(service, "contractline");
        this.unitsOfMeasureService = unitsOfMeasureService;
    }
    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractline", new ContractLine());
        model.addAttribute("units", unitsOfMeasureService.getAll());
        return "contractline/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractLine contractLine = service.getById(id);
        if (contractLine == null) {
            return "redirect:/contractlines";
        }
        model.addAttribute("contractline", contractLine);
        return "contractline/form";
    }

    @PostMapping("/{id}/edit")
    public String updateContractLine(@PathVariable String id, ContractLine contractLine) {
        contractLine.setId(id);
        service.update(id, contractLine);
        return "redirect:/contractlines";

    }
    }
