package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Contract;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepository extends InFileRepository<Contract> {
    public ContractRepository() {
        super("src/main/resources/data/contract.json",  Contract.class);
    }
}
