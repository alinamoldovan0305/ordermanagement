//package com.example.ordermanagement.model;
//
//public class ContractLine {
//
//    private String id;
//    private String itemId;   // <-- în loc de SellableItem item
//    private String unitId;   // <-- în loc de UnitsOfMeasure unit
//    private double quantity;
//
//    public ContractLine() {}
//
//    public ContractLine(String id, String itemId, String unitId, double quantity) {
//        this.id = id;
//        this.itemId = itemId;
//        this.unitId = unitId;
//        this.quantity = quantity;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }
//
//    public String getUnitId() {
//        return unitId;
//    }
//
//    public void setUnitId(String unitId) {
//        this.unitId = unitId;
//    }
//
//    public double getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(double quantity) {
//        this.quantity = quantity;
//    }
//}
package com.example.ordermanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contract_lines")
public class ContractLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ID auto-generat în loc de String

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "unit_id", nullable = false)
    private String unitId;

    @Column(nullable = false)
    private double quantity;

    // Constructor fără argumente (necesar pentru JPA)
    public ContractLine() {}

    public ContractLine(String itemId, String unitId, double quantity) {
        this.itemId = itemId;
        this.unitId = unitId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ContractLine{" +
                "id=" + id +
                ", itemId='" + itemId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
