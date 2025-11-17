package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller
//@RequestMapping("/unitsofmeasure")
//public class UnitsOfMeasureController extends GenericController<UnitsOfMeasure> {
//
//    public UnitsOfMeasureController(UnitsOfMeasureService service) {
//        super(service, "unitsofmeasure");
//    }
//
//    @Override
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("unit", new UnitsOfMeasure());
//        return "unitsofmeasure/form";
//    }
//
//    @PostMapping
//    public String createUnit(@ModelAttribute("unit") UnitsOfMeasure unit) {
//
//        String id = unit.getId();
//        if (id == null || id.isBlank()) {
//            id = String.valueOf(System.currentTimeMillis());
//            unit.setId(id);
//        }
//
//        service.add(id, unit);
//        return "redirect:/unitsofmeasure";
//    }
//
//}

@Controller
@RequestMapping("/unitsofmeasure")
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

    @Override
    @PostMapping
    public String createEntity(@ModelAttribute("unit") UnitsOfMeasure unit) {

        String id = unit.getId();
        if (id == null || id.isBlank()) {
            id = String.valueOf(System.currentTimeMillis());
            unit.setId(id);
        }

        service.add(id, unit);
        return "redirect:/unitsofmeasure";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        UnitsOfMeasure unit = service.getById(id);
        model.addAttribute("unit", unit);
        return "unitsofmeasure/form";
    }

    @PostMapping("/{id}")
    public String updateUnit(@PathVariable String id,
                             @ModelAttribute("unit") UnitsOfMeasure unit) {

        unit.setId(id);  // important: id-ul vine din URL
        service.update(id, unit);

        return "redirect:/unitsofmeasure";
    }


}