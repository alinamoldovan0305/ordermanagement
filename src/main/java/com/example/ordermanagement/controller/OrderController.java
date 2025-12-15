package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final CustomerRepository customerRepo;
    private final ContractRepository contractRepo;

    public OrderController(OrderService service,
                           CustomerRepository customerRepo,
                           ContractRepository contractRepo) {
        this.service = service;
        this.customerRepo = customerRepo;
        this.contractRepo = contractRepo;
    }

    // ---------- LIST ----------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", service.getAll());
        return "order/index";
    }

    // ---------- CREATE FORM ----------
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/form";
    }

    // ---------- CREATE ----------
    @PostMapping
    public String create(@Valid @ModelAttribute("order") Order order,
                         BindingResult bindingResult,
                         Model model) {

        bindCustomer(order, bindingResult);
        bindContract(order, bindingResult);

        if (bindingResult.hasErrors()) {
            return "order/form";
        }

        service.save(order);
        return "redirect:/orders";
    }

    // ---------- EDIT FORM ----------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = service.getById(id);
        model.addAttribute("order", order);
        return "order/form";
    }

    // ---------- UPDATE ----------
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("order") Order order,
                         BindingResult bindingResult,
                         Model model) {

        bindCustomer(order, bindingResult);
        bindContract(order, bindingResult);

        if (bindingResult.hasErrors()) {
            return "order/form";
        }

        service.update(id, order);
        return "redirect:/orders";
    }

    // ---------- DELETE ----------

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Comanda a fost stearsa cu succes.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/orders";
    }

    // ==========================================================
    //       BINDING HELPERS (customer + contract)
    // ==========================================================

    private void bindCustomer(Order order, BindingResult bindingResult) {
        if (order.getCustomer() == null || order.getCustomer().getName() == null) {
            bindingResult.rejectValue("customer", "customer.required", "Numele clientului este obligatoriu.");
            return;
        }

        String name = order.getCustomer().getName().trim();

        Customer customer = customerRepo.findByName(name)
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setName(name);
                    return customerRepo.save(c);
                });

        order.setCustomer(customer);
    }


    private void bindContract(Order order, BindingResult bindingResult) {
        if (order.getContract() == null || order.getContract().getName() == null) {
            bindingResult.rejectValue("contract", "contract.required", "Numele contractului este obligatoriu");
            return;
        }

        String name = order.getContract().getName().trim();

        Contract contract = contractRepo.findByName(name)
                .orElseGet(() -> {
                    Contract c = new Contract();
                    c.setName(name);
                    return contractRepo.save(c);
                });

        order.setContract(contract);
    }
}
