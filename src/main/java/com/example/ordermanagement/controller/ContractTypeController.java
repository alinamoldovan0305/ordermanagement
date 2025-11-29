//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.Contract;
//import com.example.ordermanagement.model.ContractType;
//import com.example.ordermanagement.service.ContractTypeService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/contracttypes")
//public class ContractTypeController extends GenericController<ContractType> {
//
//    public ContractTypeController(ContractTypeService service) {
//        super(service, "contracttype");
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("contracttype", new ContractType());
//        return "contracttype/form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable String id, Model model) {
//        ContractType contractType = service.getById(id);
//        if (contractType == null) {
//            return "redirect:/contracttypes";
//        }
//        model.addAttribute("contracttype", contractType);
//        return "contracttype/form";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateContract(@PathVariable String id, ContractType contractType) {
//        contractType.setId(id);
//        service.update(id, contractType);
//        return "redirect:/contracttypes";
//    }
//
//    @GetMapping("/{id}")
//    public String showDetails(@PathVariable String id, Model model) {
//        ContractType contractType = service.getById(id);
//        if (contractType == null) {
//            return "redirect:/contracttypes";
//        }
//        model.addAttribute("contracttype", contractType);
//        return "contracttype/details";
//    }
//
//}
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.service.ContractTypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracttypes")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    // LIST ALL
    @GetMapping
    public String list(Model model) {
        model.addAttribute("contractTypes", service.getAll());
        return "contracttype/list";
    }

    // SHOW CREATE FORM
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractType", new ContractType());
        return "contracttype/form";
    }

    // HANDLE CREATE
    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("contractType") ContractType contractType,
                         BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "contracttype/form";
        }

        // validare business: dacă numele există
        if (service.existsByName(contractType.getName())) {
            result.rejectValue("name", "error.name", "A contract type with this name already exists.");
            return "contracttype/form";
        }

        service.save(contractType);
        return "redirect:/contracttypes";
    }

    // SHOW EDIT FORM
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ContractType contractType = service.getById(id);
        if (contractType == null) {
            return "redirect:/contracttypes";
        }

        model.addAttribute("contractType", contractType);
        return "contracttype/form";
    }

    // HANDLE EDIT
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("contractType") ContractType contractType,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "contracttype/form";
        }

        contractType.setId(id);
        service.update(contractType);
        return "redirect:/contracttypes";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ContractType contractType = service.getById(id);

        if (contractType == null) {
            return "redirect:/contracttypes";
        }

        model.addAttribute("contractType", contractType);
        return "contracttype/details";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/contracttypes";
    }
}

