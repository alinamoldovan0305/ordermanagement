//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Contract;
//import com.example.ordermanagement.model.ContractLine;
//import com.example.ordermanagement.service.ContractLineService;
//import com.example.ordermanagement.service.UnitsOfMeasureService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/contractlines")
//public class ContractLineController extends GenericController<ContractLine> {
//
//    private final UnitsOfMeasureService unitsOfMeasureService;
//
//    public ContractLineController(ContractLineService service, UnitsOfMeasureService unitsOfMeasureService) {
//        super(service, "contractline");
//        this.unitsOfMeasureService = unitsOfMeasureService;
//    }
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("contractline", new ContractLine());
//        model.addAttribute("units", unitsOfMeasureService.getAll());
//        return "contractline/form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable String id, Model model) {
//        ContractLine contractLine = service.getById(id);
//        if (contractLine == null) {
//            return "redirect:/contractlines";
//        }
//        model.addAttribute("contractline", contractLine);
//        return "contractline/form";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateContractLine(@PathVariable String id, ContractLine contractLine) {
//        contractLine.setId(id);
//        service.update(id, contractLine);
//        return "redirect:/contractlines";
//
//    }
//    @GetMapping("/{id}")
//    public String details(@PathVariable String id, Model model) {
//        model.addAttribute("contractline", service.getById(id));
//        return "contractline/details";
//    }
//
//}

package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.service.ContractLineService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract-lines")
public class ContractLineController {

    private final ContractLineService contractLineService;

    public ContractLineController(ContractLineService contractLineService) {
        this.contractLineService = contractLineService;
    }

    // --------------------- GET ALL ---------------------
    @GetMapping
    public List<ContractLine> getAll() {
        return contractLineService.getAll();
    }

    // --------------------- GET BY ID ---------------------
    @GetMapping("/{id}")
    public ContractLine getById(@PathVariable Long id) {
        return contractLineService.getById(id);
    }

    // --------------------- CREATE ---------------------
    @PostMapping
    public ContractLine create(@RequestBody ContractLine contractLine) {
        return contractLineService.save(contractLine);
    }

    // --------------------- UPDATE ---------------------
    @PutMapping("/{id}")
    public ContractLine update(@PathVariable Long id, @RequestBody ContractLine updatedContractLine) {
        ContractLine existing = contractLineService.getById(id);
        if (existing == null) {
            throw new EntityNotFoundException("ContractLine not found with id: " + id);
        }

        // Setăm câmpurile actualizabile
        existing.setItem(updatedContractLine.getItem());
        existing.setUnit(updatedContractLine.getUnit());
        existing.setQuantity(updatedContractLine.getQuantity());

        return contractLineService.save(existing);
    }

    // --------------------- DELETE ---------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ContractLine existing = contractLineService.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        contractLineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


