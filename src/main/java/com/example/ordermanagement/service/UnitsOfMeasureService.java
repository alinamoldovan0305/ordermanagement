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
                        new EntityNotFoundException("Unitatea nu a fost gasita cu id: " + id));
    }

    public UnitsOfMeasure save(UnitsOfMeasure unit) {

        validateUnit(unit, true);

        return repository.save(unit);
    }

    public UnitsOfMeasure update(Long id, UnitsOfMeasure updatedUnit) {

        UnitsOfMeasure existing = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Unitatea nu a fost gasita cu id: " + id));

        updatedUnit.setId(id);

        validateUnit(updatedUnit, false);

        existing.setName(updatedUnit.getName());
        existing.setSymbol(updatedUnit.getSymbol());

        return repository.save(existing);
    }

    public void delete(Long id) {

        UnitsOfMeasure unit = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Unitatea nu a fost gasita cu id: " + id));

        if (!unit.getOrderLines().isEmpty() || !unit.getContractLines().isEmpty()) {
            throw new IllegalStateException("Unitatea nu poate fi stearsa pentru ca este folosita de un contract sau comanda.");
        }

        repository.deleteById(id);
    }


    private void validateUnit(UnitsOfMeasure unit, boolean isCreate) {

        String name = unit.getName().trim();
        String symbol = unit.getSymbol().trim();

        if (isCreate) {
            if (repository.existsByNameIgnoreCase(name)) {
                throw new IllegalArgumentException("Numele exista deja.");
            }
        } else {
            if (repository.existsByNameIgnoreCaseAndIdNot(name, unit.getId())) {
                throw new IllegalArgumentException("Exista deja o unitate cu acest nume.");
            }
        }


        if (isCreate) {
            if (repository.existsBySymbolIgnoreCase(symbol)) {
                throw new IllegalArgumentException("Simbolul exista deja.");
            }
        } else {
            if (repository.existsBySymbolIgnoreCaseAndIdNot(symbol, unit.getId())) {
                throw new IllegalArgumentException("Exista deja o alta unitate cu acest simbol.");
            }
        }
    }
}

