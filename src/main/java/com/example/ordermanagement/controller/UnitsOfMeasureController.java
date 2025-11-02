package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class UnitsOfMeasureController {

    private final UnitsOfMeasureService unitsOfMeasureService;

    public UnitsOfMeasureController(UnitsOfMeasureService unitsOfMeasureService) {
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    @GetMapping
    public List<UnitsOfMeasure> getUnitsOfMeasure() {
        return unitsOfMeasureService.getAllUnits();
    }

    @GetMapping
    public UnitsOfMeasure getUnitsOfMeasure(@RequestParam String id) {
        return unitsOfMeasureService.getUnitById(id);
    }

    @PostMapping
    public void createUnitsOfMeasure(@RequestBody UnitsOfMeasure unitsOfMeasure) {
        unitsOfMeasureService.saveUnit(unitsOfMeasure);
    }

    @DeleteMapping
    public void deleteUnitsOfMeasure(@RequestParam String id) {
        unitsOfMeasureService.deleteUnit(id);
    }
}
