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
                .orElseThrow(() ->
                        new EntityNotFoundException("UnitsOfMeasure not found with id: " + id));
    }

    public UnitsOfMeasure save(UnitsOfMeasure unit) {

        validateUnit(unit, true);

        return repository.save(unit);
    }

    public UnitsOfMeasure update(Long id, UnitsOfMeasure updatedUnit) {

        UnitsOfMeasure existing = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("UnitsOfMeasure not found with id: " + id));

        updatedUnit.setId(id);

        validateUnit(updatedUnit, false);

        existing.setName(updatedUnit.getName());
        existing.setSymbol(updatedUnit.getSymbol());

        return repository.save(existing);
    }

    public void delete(Long id) {

        UnitsOfMeasure unit = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("UnitsOfMeasure not found with id: " + id));

        // NU PERMIȚI ȘTERGEREA DACĂ ESTE FOLOSITĂ
        if (!unit.getOrderLines().isEmpty() || !unit.getContractLines().isEmpty()) {
            throw new IllegalStateException("Cannot delete unit because it is used in contracts or orders.");
        }

        repository.deleteById(id);
    }

    // ---------------- VALIDATOR ----------------

    private void validateUnit(UnitsOfMeasure unit, boolean isCreate) {

        String name = unit.getName().trim();
        String symbol = unit.getSymbol().trim();

        // 1. NAME UNIC
        if (isCreate) {
            if (repository.existsByNameIgnoreCase(name)) {
                throw new IllegalArgumentException("Unit name already exists.");
            }
        } else {
            if (repository.existsByNameIgnoreCaseAndIdNot(name, unit.getId())) {
                throw new IllegalArgumentException("Another unit with this name already exists.");
            }
        }

        // 2. SYMBOL UNIC
        if (isCreate) {
            if (repository.existsBySymbolIgnoreCase(symbol)) {
                throw new IllegalArgumentException("Unit symbol already exists.");
            }
        } else {
            if (repository.existsBySymbolIgnoreCaseAndIdNot(symbol, unit.getId())) {
                throw new IllegalArgumentException("Another unit with this symbol already exists.");
            }
        }
    }
}

