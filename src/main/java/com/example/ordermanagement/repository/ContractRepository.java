
package com.example.ordermanagement.repository;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface
ContractRepository extends JpaRepository<Contract, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndCustomerId(String name, Long customerId);
    Optional<Contract> findByName(String name);
    void deleteByCustomerIdAndStatus(Long customerId, ContractStatus status);
    boolean existsByCustomerIdAndStatus(Long customerId, ContractStatus status);
    long countByCustomerIdAndStatus(Long customerId, ContractStatus status);
    List<Contract> findByCustomerId(Long customerId);
    List<Contract> findByCustomer_NameContainingIgnoreCaseAndStatus(
            String customerName,
            ContractStatus status,
            Sort sort
    );
    List<Contract> findByCustomer_NameContainingIgnoreCase(
            String customerName,
            Sort sort
    );

    List<Contract> findByStatus(
            ContractStatus status,
            Sort sort
    );


}


