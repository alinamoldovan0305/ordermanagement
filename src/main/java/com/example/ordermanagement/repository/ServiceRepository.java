
package com.example.ordermanagement.repository;

import com.example.ordermanagement.enums.ServiceStatus;
import com.example.ordermanagement.model.ServiceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    // CRUD implicit prin JpaRepository
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    List<ServiceEntity> findByNameContainingIgnoreCase(String name, Sort sort);

    List<ServiceEntity> findByStatus(ServiceStatus status, Sort sort);

    List<ServiceEntity> findByNameContainingIgnoreCaseAndStatus(
            String name,
            ServiceStatus status,
            Sort sort
    );

}
