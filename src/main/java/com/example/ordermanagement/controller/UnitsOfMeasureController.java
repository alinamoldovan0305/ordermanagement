
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/unitsofmeasures")
public class UnitsOfMeasureController extends GenericController<UnitsOfMeasure> {

    public UnitsOfMeasureController(UnitsOfMeasureService service) {
        super(service, "unitsofmeasure");
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("unit", new UnitsOfMeasure());
        return "unitsofmeasure/form";
    }

    // --- CREATE ---
    @Override
    @PostMapping
    public String createEntity(@ModelAttribute("unit") UnitsOfMeasure unit) {

        String id = unit.getId();
        if (id == null || id.isBlank()) {
            id = String.valueOf(System.currentTimeMillis());
            unit.setId(id);
        }

        service.add(id, unit);
        return "redirect:/unitsofmeasures";
    }

//    // --- DELETE ---
//    @PostMapping("/{id}/delete")
//    public String deleteUnit(@PathVariable String id) {
//        service.delete(id);
//        return "redirect:/unitsofmeasure";
//    }


    // --- EDIT (GET) ---
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        UnitsOfMeasure unit = service.getById(id);

        if (unit == null) {
            return "redirect:/unitsofmeasures";
        }

        model.addAttribute("unit", unit);
        return "unitsofmeasure/form";
    }

    // --- EDIT (POST) ---
    @PostMapping("/{id}/edit")
    public String updateUnit(@PathVariable String id,
                             @ModelAttribute("unit") UnitsOfMeasure unit) {

        unit.setId(id);
        service.update(id, unit);

        return "redirect:/unitsofmeasures";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable String id, Model model) {
        UnitsOfMeasure unit = service.getById(id);
        if (unit == null) {
            return "redirect:/unitsofmeasures"; // dacă nu există, redirect la listă
        }
        model.addAttribute("unit", unit);
        return "unitsofmeasure/details";
    }

}
