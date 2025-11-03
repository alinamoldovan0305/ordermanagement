package com.example.ordermanagement.service;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitsOfMeasureService extends GenericService<UnitsOfMeasure> {
    public UnitsOfMeasureService(UnitsOfMeasureRepository repository) {
        super(repository);
    }
}