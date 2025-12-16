package com.example.ordermanagement.controller;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.SellableItemRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import com.example.ordermanagement.service.ContractLineService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String list(
            @RequestParam(required = false) String contractName,
            @RequestParam(required = false) String itemName,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        model.addAttribute(
                "lines",
                service.filterAndSort(contractName, itemName, sortBy, direction)
        );

        // preserve values
        model.addAttribute("contractName", contractName);
        model.addAttribute("itemName", itemName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

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
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("contracts", contractRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "contractline/form";
        }

        try {
            service.save(line);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Linia de contract a fost adăugată cu succes."
            );
            return "redirect:/contract-lines";

        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("contracts", contractRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "contractline/form";
        }
    }


    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ContractLine line = service.getById(id);
        model.addAttribute("contractLine", line);  // <-- schimbat aici
        return "contractline/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        ContractLine line = service.getById(id);

        if (line.getContract().getStatus() == ContractStatus.DOWN) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Nu poți edita linii pentru un contract inactiv."
            );
            return "redirect:/contract-lines";
        }

        model.addAttribute("contractLine", line);
        model.addAttribute("contracts", contractRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "contractline/form";
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("contractLine") ContractLine line,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("contracts", contractRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "contractline/form";
        }

        try {
            service.update(id, line);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Linia de contract a fost actualizată cu succes."
            );
            return "redirect:/contract-lines";

        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("contracts", contractRepo.findAll());
            model.addAttribute("items", itemRepo.findAll());
            model.addAttribute("units", unitRepo.findAll());
            return "contractline/form";
        }
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/contract-lines";
    }
}
