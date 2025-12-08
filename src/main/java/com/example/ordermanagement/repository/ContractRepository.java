
package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
ContractRepository extends JpaRepository<Contract, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndCustomerId(String name, Long customerId);
    Optional<Contract> findByName(String name);


}


