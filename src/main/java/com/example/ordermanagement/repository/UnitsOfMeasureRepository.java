package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.UnitsOfMeasure;
import org.springframework.stereotype.Repository;

@Repository
public class UnitsOfMeasureRepository extends InFileRepository<UnitsOfMeasure> {
    public UnitsOfMeasureRepository() {
        super("src/main/resources/data/unitsOfMeasure.json", UnitsOfMeasure.class);
    }
}
