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

//    @GetMapping
//    public String listCustomers(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String currency,
//            @RequestParam(defaultValue = "name") String sortBy,
//            @RequestParam(defaultValue = "asc") String direction,
//            Model model) {
//
//        List<Customer> customers =
//                customerService.filterCustomers(name, currency, sortBy, direction);
//
//        model.addAttribute("customers", customers);
//        model.addAttribute("name", name);
//        model.addAttribute("currency", currency);
//        model.addAttribute("sortBy", sortBy);
//        model.addAttribute("direction", direction);
//
//        return "customers/index";
//    }
    @GetMapping
    public String listCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String currency,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        List<Customer> customers =
                customerService.filterAndSort(name, currency, sortBy, direction);

        Map<Long, List<Contract>> contractsMap = new HashMap<>();
        for (Customer c : customers) {
            contractsMap.put(
                    c.getId(),
                    customerService.getContractsByCustomerId(c.getId())
            );
        }

        // DATA
        model.addAttribute("customers", customers);
        model.addAttribute("contractsMap", contractsMap);

        // PRESERVE FILTER/SORT VALUES
        model.addAttribute("name", name);
        model.addAttribute("currency", currency);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "customers/index";
    }


    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @ModelAttribute("customer") Customer customer,
                         Model model) {

        try {
            customerService.update(id, customer);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "customers/form";
        }

        return "redirect:/customers";
    }


    //    @PostMapping
//    public String createCustomer(@Valid @ModelAttribute("customer") Customer customer,
//                                 BindingResult result,
//                                 Model model) {
//
//        if (result.hasErrors()) {
//            return "customers/form";
//        }
//
//        try {
//            customerService.save(customer);
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "customers/form";
//        }
//
//        return "redirect:/customers";
//    }
    @PostMapping
    public String create(@ModelAttribute("customer") Customer customer,
                         Model model) {

        try {
            customerService.save(customer);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "customers/form";
        }

        return "redirect:/customers";
    }

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


    @GetMapping("/{id}/edit")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customers/form";
    }

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
