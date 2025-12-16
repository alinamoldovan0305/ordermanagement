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

    @GetMapping
    public String list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) Boolean delivered,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        model.addAttribute(
                "orders",
                service.filterAndSort(name, customerName, delivered, sortBy, direction)
        );

        model.addAttribute("name", name);
        model.addAttribute("customerName", customerName);
        model.addAttribute("delivered", delivered);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "order/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/form";
    }

//    @PostMapping
//    public String create(@Valid @ModelAttribute("order") Order order,
//                         BindingResult bindingResult,
//                         Model model) {
//
//        bindCustomer(order, bindingResult);
//        bindContract(order, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "order/form";
//        }
//
//        service.save(order);
//        return "redirect:/orders";
//    }
    @PostMapping
    public String create(@Valid @ModelAttribute("order") Order order,
                         BindingResult bindingResult,
                         Model model) {

        if (order.getName() == null || order.getName().trim().isEmpty()) {
            bindingResult.rejectValue("name", "name.required", "Numele este obligatoriu.");
        }

        bindCustomer(order, bindingResult);
        bindContract(order, bindingResult);

        if (bindingResult.hasErrors()) {
            return "order/form";
        }

        service.save(order);
        return "redirect:/orders";
    }



    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = service.getById(id);
        model.addAttribute("order", order);
        return "order/form";
    }


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

    private void bindCustomer(Order order, BindingResult bindingResult) {

        if (order.getCustomer() == null || order.getCustomer().getName() == null ||
                order.getCustomer().getName().trim().isEmpty()) {

            bindingResult.rejectValue(
                    "customer",
                    "customer.required",
                    "Clientul este obligatoriu."
            );
            return;
        }

        String name = order.getCustomer().getName().trim();

        Customer customer = customerRepo.findByName(name).orElse(null);

        if (customer == null) {
            bindingResult.rejectValue(
                    "customer",
                    "customer.invalid",
                    "Clientul nu există."
            );
            return;
        }

        order.setCustomer(customer);
    }

    private void bindContract(Order order, BindingResult bindingResult) {

        if (order.getContract() == null || order.getContract().getName() == null ||
                order.getContract().getName().trim().isEmpty()) {

            bindingResult.rejectValue(
                    "contract",
                    "contract.required",
                    "Contractul este obligatoriu."
            );
            return;
        }

        String name = order.getContract().getName().trim();

        Contract contract = contractRepo.findByName(name).orElse(null);

        if (contract == null) {
            bindingResult.rejectValue(
                    "contract",
                    "contract.invalid",
                    "Contractul selectat nu există."
            );
            return;
        }

        order.setContract(contract);
    }


    @GetMapping("/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {
        model.addAttribute("order", service.getById(id));
        return "order/details";
    }

}
