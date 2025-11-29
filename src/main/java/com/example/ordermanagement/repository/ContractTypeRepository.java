//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.ContractType;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ContractTypeRepository extends InFileRepository<ContractType> {
//    public ContractTypeRepository() {
//        super("src/main/resources/data/contractType.json",  ContractType.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
    boolean existsByName(String name);
}
