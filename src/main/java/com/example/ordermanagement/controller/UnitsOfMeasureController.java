package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/units-view")
public class UnitsOfMeasureController extends GenericController<UnitsOfMeasure> {

    public UnitsOfMeasureController(UnitsOfMeasureService service) {
        super(service, "unit");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("unit", new UnitsOfMeasure());
        return "unit/form";
    }
}
