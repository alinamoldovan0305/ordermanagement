package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractLine;
import org.springframework.stereotype.Repository;

@Repository
public class ContractLineRepository extends InFileRepository<ContractLine> {

    public ContractLineRepository() {
        super("src/main/resources/data/contractLine.json", ContractLine.class);
    }
}
