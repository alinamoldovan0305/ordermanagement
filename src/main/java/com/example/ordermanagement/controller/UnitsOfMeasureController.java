package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unitsofmeasure")
public class UnitsOfMeasureController {

    private final UnitsOfMeasureService unitsOfMeasureService;

    public UnitsOfMeasureController(UnitsOfMeasureService unitsOfMeasureService) {
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    @GetMapping
    public List<UnitsOfMeasure> getAll() {
        return unitsOfMeasureService.getAll();
    }

    @GetMapping("/{id}")
    public UnitsOfMeasure getById(@PathVariable String id) {
        return unitsOfMeasureService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody UnitsOfMeasure unitsOfMeasure) {
        unitsOfMeasureService.add(unitsOfMeasure.getId(), unitsOfMeasure);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody UnitsOfMeasure unitsOfMeasure) {
        unitsOfMeasureService.update(id, unitsOfMeasure);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        unitsOfMeasureService.delete(id);
    }
}
