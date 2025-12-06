

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ContractLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractLineRepository extends JpaRepository<ContractLine, Long> {
}

