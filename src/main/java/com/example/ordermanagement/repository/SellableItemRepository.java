//package com.example.ordermanagement.repository;
//
//import com.example.ordermanagement.model.SellableItem;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class SellableItemRepository extends InFileRepository<SellableItem> {
//    public SellableItemRepository() {
//        super("src/main/resources/data/sellableItem.json",  SellableItem.class);
//    }
//}

package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.SellableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellableItemRepository extends JpaRepository<SellableItem, Long> {
    // metode CRUD sunt deja disponibile
}
