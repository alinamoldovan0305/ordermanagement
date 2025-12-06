//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.ContractLine;
//import com.example.ordermanagement.repository.ContractRepository;
//import com.example.ordermanagement.repository.SellableItemRepository;
//import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
//import com.example.ordermanagement.service.ContractLineService;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import com.example.ordermanagement.model.Contract;
//import com.example.ordermanagement.model.SellableItem;
//import com.example.ordermanagement.model.UnitsOfMeasure;
//
//import com.example.ordermanagement.editor.ContractEditor;
//import com.example.ordermanagement.editor.ItemEditor;
//import com.example.ordermanagement.editor.UnitEditor;
//import com.example.ordermanagement.service.ContractService;
//import com.example.ordermanagement.service.SellableItemService;
//import com.example.ordermanagement.service.UnitsOfMeasureService;
//
//
//@Controller
//@RequestMapping("/contract-lines")
//public class ContractLineController {
//
//    private final ContractLineService service;
//    private final ContractRepository contractRepo;
//    private final SellableItemRepository itemRepo;
//    private final UnitsOfMeasureRepository unitRepo;
//
//    public ContractLineController(ContractLineService service,
//                                  ContractRepository contractRepo,
//                                  SellableItemRepository itemRepo,
//                                  UnitsOfMeasureRepository unitRepo) {
//        this.service = service;
//        this.contractRepo = contractRepo;
//        this.itemRepo = itemRepo;
//        this.unitRepo = unitRepo;
//    }
//
//    // ---------- LIST ----------
//    @GetMapping
//    public String list(Model model) {
//        model.addAttribute("lines", service.getAll());
//        return "contractline/index";
//    }
//
//    // ---------- CREATE FORM ----------
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("contractLine", new ContractLine());
//        model.addAttribute("contracts", contractRepo.findAll());
//        model.addAttribute("items", itemRepo.findAll());
//        model.addAttribute("units", unitRepo.findAll());
//        return "contractline/form";
//    }
//
//    // ---------- CREATE ----------
//    @PostMapping
//    public String create(@Valid @ModelAttribute("contractLine") ContractLine line,
//                         BindingResult bindingResult,
//                         Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("contracts", contractRepo.findAll());
//            model.addAttribute("items", itemRepo.findAll());
//            model.addAttribute("units", unitRepo.findAll());
//            return "contractline/form";
//        }
//
//        service.save(line);
//        return "redirect:/contract-lines";
//    }
//
//    // ---------- EDIT FORM ----------
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        ContractLine line = service.getById(id);
//
//        model.addAttribute("contractLine", line);
//        model.addAttribute("contracts", contractRepo.findAll());
//        model.addAttribute("items", itemRepo.findAll());
//        model.addAttribute("units", unitRepo.findAll());
//
//        return "contractline/form";
//    }
//
//    // ---------- UPDATE ----------
//    @PostMapping("/{id}/edit")
//    public String update(@PathVariable Long id,
//                         @Valid @ModelAttribute("contractLine") ContractLine line,
//                         BindingResult bindingResult,
//                         Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("contracts", contractRepo.findAll());
//            model.addAttribute("items", itemRepo.findAll());
//            model.addAttribute("units", unitRepo.findAll());
//            return "contractline/form";
//        }
//
//        service.update(id, line);   // <-- AICI E FIX-UL
//
//        return "redirect:/contract-lines";
//    }
//
//    // ---------- DELETE ----------
//    @PostMapping("/{id}/delete")
//    public String delete(@PathVariable Long id) {
//        service.delete(id);
//        return "redirect:/contract-lines";
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Contract.class, new ContractEditor(contractService));
//        binder.registerCustomEditor(SellableItem.class, new ItemEditor(sellableItemService));
//        binder.registerCustomEditor(UnitsOfMeasure.class, new UnitEditor(unitsOfMeasureService));
//    }
//
//}
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

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.model.UnitsOfMeasure;

import com.example.ordermanagement.editor.ContractEditor;
import com.example.ordermanagement.editor.ItemEditor;
import com.example.ordermanagement.editor.UnitEditor;

import com.example.ordermanagement.service.ContractService;
import com.example.ordermanagement.service.SellableItemService;
import com.example.ordermanagement.service.UnitsOfMeasureService;

import org.springframework.web.bind.WebDataBinder;

@Controller
@RequestMapping("/contract-lines")
public class ContractLineController {

    private final ContractLineService service;
    private final ContractRepository contractRepo;
    private final SellableItemRepository itemRepo;
    private final UnitsOfMeasureRepository unitRepo;

    private final ContractService contractService;
    private final SellableItemService sellableItemService;
    private final UnitsOfMeasureService unitsOfMeasureService;


    public ContractLineController(ContractLineService service,
                                  ContractRepository contractRepo,
                                  SellableItemRepository itemRepo,
                                  UnitsOfMeasureRepository unitRepo,
                                  ContractService contractService,
                                  SellableItemService sellableItemService,
                                  UnitsOfMeasureService unitsOfMeasureService) {

        this.service = service;
        this.contractRepo = contractRepo;
        this.itemRepo = itemRepo;
        this.unitRepo = unitRepo;

        // injectăm serviciile necesare editorilor
        this.contractService = contractService;
        this.sellableItemService = sellableItemService;
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("lines", service.getAll());
        return "contractline/index";
    }

    // ---------- DETAILS ----------
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ContractLine line = service.getById(id);
        model.addAttribute("contractline", line);
        return "contractline/details";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractLine", new ContractLine());
        model.addAttribute("contracts", contractRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        return "contractline/form";
    }

    // ---------- CREATE ----------
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

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ContractLine line = service.getById(id);

        model.addAttribute("contractLine", line);
        model.addAttribute("contracts", contractRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());

        return "contractline/form";
    }

    // ---------- UPDATE ----------
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

        service.update(id, line);  // fix corect
        return "redirect:/contract-lines";
    }

    // ---------- DELETE ----------
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/contract-lines";
    }

    // ---------- BINDER (ID → obiect) ----------
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Contract.class,
                new ContractEditor(contractService));

        binder.registerCustomEditor(SellableItem.class,
                new ItemEditor(sellableItemService));

        binder.registerCustomEditor(UnitsOfMeasure.class,
                new UnitEditor(unitsOfMeasureService));
    }
}
