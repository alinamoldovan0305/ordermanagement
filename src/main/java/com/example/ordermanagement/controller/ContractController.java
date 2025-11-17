//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Contract;
//import com.example.ordermanagement.service.ContractService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
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
//}
//
//
//
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Contract contract = service.getById(id);
        if (contract == null) {
            return "redirect:/contracts";
        }
        model.addAttribute("contract", contract);
        return "contract/form";
    }

    @PostMapping("/{id}/edit")
    public String updateContract(@PathVariable String id, Contract contract) {
        contract.setId(id);
        service.update(id, contract);
        return "redirect:/contracts";
    }
}
