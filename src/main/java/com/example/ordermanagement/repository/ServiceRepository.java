//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.ServiceEntity;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ServiceRepository extends InFileRepository<ServiceEntity> {
//    public ServiceRepository() {
//        super("src/main/resources/data/service.json",  ServiceEntity.class);
//    }
//}
package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    // CRUD implicit prin JpaRepository
}
