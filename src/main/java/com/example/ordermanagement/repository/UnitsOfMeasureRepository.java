//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.UnitsOfMeasure;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class UnitsOfMeasureRepository extends InFileRepository<UnitsOfMeasure> {
//    public UnitsOfMeasureRepository() {
//        super("src/main/resources/data/unitsOfMeasure.json", UnitsOfMeasure.class);
//    }
//}
package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.UnitsOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsOfMeasureRepository extends JpaRepository<UnitsOfMeasure, Long> {
    // CRUD standard prin JpaRepository
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    boolean existsBySymbolIgnoreCase(String symbol);
    boolean existsBySymbolIgnoreCaseAndIdNot(String symbol, Long id);

}
