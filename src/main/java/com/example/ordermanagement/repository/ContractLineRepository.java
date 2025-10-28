package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractLine;
import java.util.*;

public class ContractLineRepository {

    private Map<String, ContractLine> contractLines = new HashMap<>();

    public void save(ContractLine contractLine) {
        contractLines.put(contractLine.getId(), contractLine);
    }

    public List<ContractLine> findAll() {
        return new ArrayList<>(contractLines.values());
    }

    public ContractLine findById(String id) {
        return contractLines.get(id);
    }

    public void delete(String id) {
        contractLines.remove(id);
    }

//    public void update(String id, ContractLine updatedContractLine) {
//        if(contractLines.containsKey(id)) {
//            contractLines.put(id, updatedContractLine);
//        }
//    }
}

