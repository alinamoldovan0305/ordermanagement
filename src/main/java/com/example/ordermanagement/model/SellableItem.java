//package com.example.ordermanagement.model;
//
//public abstract class SellableItem {
//    private String id;
//    private String name;
//
//    public SellableItem(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public SellableItem() {}
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return name + "(ID: " + id + ")" ;
//    }
//}
package com.example.ordermanagement.model;

import jakarta.persistence.*;

@Entity  // nu @MappedSuperclass aici
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class SellableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public SellableItem() {}

    public SellableItem(String name) {
        this.name = name;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
