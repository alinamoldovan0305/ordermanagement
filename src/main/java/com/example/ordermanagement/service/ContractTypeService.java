
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ContractType;
import com.example.ordermanagement.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractTypeService {

    private final ContractTypeRepository repository;

    public ContractTypeService(ContractTypeRepository repository) {
        this.repository = repository;
    }


    public List<ContractType> getAll() {
        return repository.findAll();
    }


    public ContractType getById(Long id) {
        Optional<ContractType> ct = repository.findById(id);
        return ct.orElse(null);
    }

    public ContractType save(ContractType contractType) {
        return repository.save(contractType);
    }

    public ContractType update(ContractType contractType) {
        if (contractType.getId() != null && repository.existsById(contractType.getId())) {
            return repository.save(contractType);
        }
        return null;
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
    private void validateType(ContractType type) {

        if (type.getName() == null || type.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }

        if (type.getId() == null) {
            if (repository.existsByNameIgnoreCase(type.getName())) {
                throw new IllegalArgumentException("A contract type with this name already exists!");
            }
        }

        else {
            if (repository.existsByNameIgnoreCaseAndIdNot(type.getName(), type.getId())) {
                throw new IllegalArgumentException("A contract type with this name already exists!");
            }
        }
    }
}
