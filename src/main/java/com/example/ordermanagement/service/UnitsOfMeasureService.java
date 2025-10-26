package com.example.ordermanagement.service;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import java.util.List;

public class UnitsOfMeasureService {

    private UnitsOfMeasureRepository unitsOfMeasureRepository = new UnitsOfMeasureRepository();

    public void saveUnit(UnitsOfMeasure unit) {
        unitsOfMeasureRepository.save(unit);
    }

    public List<UnitsOfMeasure> getAllUnits() {
        return unitsOfMeasureRepository.findAll();
    }

    public UnitsOfMeasure getUnitById(String id) {
        return unitsOfMeasureRepository.findById(id);
    }

    public void deleteUnit(String id) {
        unitsOfMeasureRepository.delete(id);
    }
}

