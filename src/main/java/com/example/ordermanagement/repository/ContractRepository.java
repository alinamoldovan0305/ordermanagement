package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Contract;
import java.util.*;

public class ContractRepository {

    private Map<String, Contract> contracts = new HashMap<>();

    public void save(Contract contract) {
        contracts.put(contract.getId(), contract);
    }

    public List<Contract> findAll() {
        return new ArrayList<>(contracts.values());
    }

    public Contract findById(String id) {
        return contracts.get(id);
    }

    public void delete(String id) {
        contracts.remove(id);
    }

//    public void update(String id, Contract updatedContract) {
//        if (contracts.containsKey(id)) {
//            contracts.put(id, updatedContract);
//        }
//    }
}
