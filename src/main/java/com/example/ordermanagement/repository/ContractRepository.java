//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.Contract;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ContractRepository extends InFileRepository<Contract> {
//    public ContractRepository() {
//        super("src/main/resources/data/contract.json",  Contract.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    // Exemple de metode custom (opționale):
    boolean existsByName(String name);

}


