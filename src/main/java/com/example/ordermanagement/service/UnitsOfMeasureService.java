package com.example.ordermanagement.service;

import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitsOfMeasureService {

    private final UnitsOfMeasureRepository repository;

    public UnitsOfMeasureService(UnitsOfMeasureRepository repository) {
        this.repository = repository;
    }

    public List<UnitsOfMeasure> getAll() {
        return repository.findAll();
    }

    public UnitsOfMeasure getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UnitsOfMeasure not found with id: " + id));
    }

    public UnitsOfMeasure save(UnitsOfMeasure unit) {
        return repository.save(unit);
    }

    public UnitsOfMeasure update(Long id, UnitsOfMeasure updatedUnit) {
        UnitsOfMeasure existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UnitsOfMeasure not found with id: " + id));

        existing.setName(updatedUnit.getName());
        existing.setSymbol(updatedUnit.getSymbol());

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("UnitsOfMeasure not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
