

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // metode custom opționale
    boolean existsById(Long id);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    Optional<Customer> findByName(String name);

}


