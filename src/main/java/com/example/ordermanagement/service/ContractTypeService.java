//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.ContractType;
//import com.example.ordermanagement.repository.ContractTypeRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ContractTypeService extends GenericService<ContractType> {
//    public ContractTypeService(ContractTypeRepository repository) {
//        super(repository);
//    }
//}
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

    // GET ALL
    public List<ContractType> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public ContractType getById(Long id) {
        Optional<ContractType> ct = repository.findById(id);
        return ct.orElse(null);
    }

    // CREATE
    public ContractType save(ContractType contractType) {
        return repository.save(contractType);
    }

    // UPDATE
    public ContractType update(ContractType contractType) {
        if (contractType.getId() != null && repository.existsById(contractType.getId())) {
            return repository.save(contractType);
        }
        return null;
    }

    // DELETE
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

}
