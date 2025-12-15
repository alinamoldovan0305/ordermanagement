package com.example.ordermanagement.controller;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // --------------------- LISTA ---------------------

//    @GetMapping
//    public String listCustomers(Model model) {
//        List<Customer> customers = customerService.getAll();
//
//        Map<Long, List<Contract>> contractsMap = new HashMap<>();
//
//        for (Customer c : customers) {
//            contractsMap.put(
//                    c.getId(),
//                    customerService.getContractsByCustomerId(c.getId())
//            );
//        }
//
//        model.addAttribute("customers", customers);
//        model.addAttribute("contractsMap", contractsMap);
//
//        return "customers/index";
//    }
    @GetMapping
    public String listCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String currency,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model) {

        List<Customer> customers =
                customerService.filterCustomers(name, currency, sortBy, direction);

        model.addAttribute("customers", customers);
        model.addAttribute("name", name);
        model.addAttribute("currency", currency);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "customers/index";
    }





    // --------------------- FORMULAR CREARE ---------------------
    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    // --------------------- SALVARE CREARE ---------------------
    @PostMapping
    public String createCustomer(@Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "customers/form";
        }

        try {
            customerService.save(customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "customers/form";
        }

        return "redirect:/customers";
    }

    // --------------------- DETALII ---------------------
    @GetMapping("/{id}")
    public String customerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);

        long activeContracts = customerService.getActiveContractCount(id);
        long orderCount = customerService.getOrderCount(id);

        model.addAttribute("customer", customer);
        model.addAttribute("activeContracts", activeContracts);
        model.addAttribute("orderCount", orderCount);

        return "customers/details";
    }




    // --------------------- FORMULAR EDITARE ---------------------
    @GetMapping("/{id}/edit")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customers/form";
    }

    // --------------------- SALVARE EDITARE ---------------------
    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "customers/form";
        }

        try {
            customerService.update(id, customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "customers/form";
        }

        return "redirect:/customers";
    }

    // --------------------- ȘTERGERE ---------------------
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            customerService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Clientul a fost sters cu succes.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare neasteptata in stergerea clientului.");
        }

        return "redirect:/customers";
    }

}
