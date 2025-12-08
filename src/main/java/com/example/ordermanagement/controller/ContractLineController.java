package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.SellableItemRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import com.example.ordermanagement.service.ContractLineService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-lines")
public class ContractLineController {

    private final ContractLineService service;
    private final ContractRepository contractRepo;
    private final SellableItemRepository itemRepo;
    private final UnitsOfMeasureRepository unitRepo;

    public ContractLineController(ContractLineService service,
                                  ContractRepository contractRepo,
                                  SellableItemRepository itemRepo,
                                  UnitsOfMeasureRepository unitRepo) {
        this.service = service;
        this.contractRepo = contractRepo;
        this.itemRepo = itemRepo;
        this.unitRepo = unitRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("lines", service.getAll());
        return "contractline/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractLine", new ContractLine());
        model.addAttribute("contracts", contractRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "contractline/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("contractLine") ContractLine line,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("contracts", contractRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "contractline/form";
        }

        service.save(line);
        return "redirect:/contract-lines";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ContractLine line = service.getById(id);
        model.addAttribute("contractLine", line);
        model.addAttribute("contracts", contractRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "contractline/form";
    }
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ContractLine line = service.getById(id);
        model.addAttribute("contractLine", line);  // <-- schimbat aici
        return "contractline/details";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("contractLine") ContractLine line,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("contracts", contractRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "contractline/form";
        }

        service.update(id, line);
        return "redirect:/contract-lines";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/contract-lines";
    }
}
