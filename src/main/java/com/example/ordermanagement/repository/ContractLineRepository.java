//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.ContractLine;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ContractLineRepository extends InFileRepository<ContractLine> {
//
//    public ContractLineRepository() {
//        super("src/main/resources/data/contractLine.json", ContractLine.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractLineRepository extends JpaRepository<ContractLine, Long> {
}

