//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Contract;
//import com.example.ordermanagement.service.ContractService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/contracts")
//public class ContractController extends GenericController<Contract> {
//
//    public ContractController(ContractService service) {
//        super(service, "contract");
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("contract", new Contract());
//        return "contract/form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable String id, Model model) {
//        Contract contract = service.getById(id);
//        if (contract == null) {
//            return "redirect:/contracts";
//        }
//        model.addAttribute("contract", contract);
//        return "contract/form";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateContract(@PathVariable String id, Contract contract) {
//        contract.setId(id);
//        service.update(id, contract);
//        return "redirect:/contracts";
//    }
//
//    @GetMapping("/{id}")
//    public String details(@PathVariable String id, Model model) {
//        model.addAttribute("contract", service.getById(id));
//        return "contract/details";
//    }
//
//}
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    // --------------------- GET ALL ---------------------
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAll();
        return ResponseEntity.ok(contracts);
    }

    // --------------------- GET BY ID ---------------------
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Contract contract = contractService.getById(id);
        if (contract != null) {
            return ResponseEntity.ok(contract);
        }
        return ResponseEntity.notFound().build();
    }

    // --------------------- CREATE ---------------------
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        try {
            Contract savedContract = contractService.save(contract);
            return ResponseEntity.ok(savedContract);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // returnăm 400 dacă relațiile nu există
        }
    }

    // --------------------- UPDATE ---------------------
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract contract) {
        contract.setId(id); // asigurăm că ID-ul vine din path
        try {
            Contract updatedContract = contractService.update(contract);
            if (updatedContract != null) {
                return ResponseEntity.ok(updatedContract);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // --------------------- DELETE ---------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        try {
            contractService.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
