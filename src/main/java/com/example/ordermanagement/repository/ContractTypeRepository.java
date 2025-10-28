package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractType;
import java.util.*;

public class ContractTypeRepository {

    private Map<String, ContractType> contractTypes = new HashMap<>();

    public void save(ContractType contractType) {
        contractTypes.put(contractType.getId(), contractType);
    }

    public List<ContractType> findAll() {
        return new ArrayList<>(contractTypes.values());
    }

    public ContractType findById(String id) {
        return contractTypes.get(id);
    }

    public void delete(String id) {
        contractTypes.remove(id);
    }

//    public void update(String id, ContractType contractType) {
//        if (contractTypes.containsKey(id)) {
//            contractTypes.put(id, contractType);
//        }
//    }
}

