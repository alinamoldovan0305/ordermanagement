package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.UnitsOfMeasure;
import java.util.*;

public class UnitsOfMeasureRepository {

    private Map<String, UnitsOfMeasure> units = new HashMap<>();

    public void save(UnitsOfMeasure unit) {
        units.put(unit.getId(), unit);
    }

    public List<UnitsOfMeasure> findAll() {
        return new ArrayList<>(units.values());
    }

    public UnitsOfMeasure findById(String id) {
        return units.get(id);
    }

    public void delete(String id) {
        units.remove(id);
    }

    public void update(String id, UnitsOfMeasure updatedUnit) {
        if(units.containsKey(id)) {
            units.put(id, updatedUnit);
        }
    }
}
