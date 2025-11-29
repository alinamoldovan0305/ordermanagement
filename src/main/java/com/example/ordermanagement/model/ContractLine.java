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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contract_lines")
public class ContractLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELAȚIA M:1 CU Contract
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    @NotNull(message = "Contract is required")
    private Contract contract;

    // RELAȚIA M:1 CU SellableItem (produs sau serviciu)
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @NotNull(message = "Item is required")
    private SellableItem item;

    // RELAȚIA M:1 CU UnitOfMeasure
    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    @NotNull(message = "Unit is required")
    private UnitsOfMeasure unit;

    @Min(value = 1, message = "Quantity must be positive")
    private double quantity;

    public ContractLine() {}

    public ContractLine(Contract contract, SellableItem item, UnitsOfMeasure unit, double quantity) {
        this.contract = contract;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public SellableItem getItem() { return item; }
    public void setItem(SellableItem item) { this.item = item; }

    public UnitsOfMeasure getUnit() { return unit; }
    public void setUnit(UnitsOfMeasure unit) { this.unit = unit; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "ContractLine{" +
                "id=" + id +
                ", contractId=" + (contract != null ? contract.getId() : "N/A") +
                ", item=" + (item != null ? item.getName() : "N/A") +
                ", unit=" + (unit != null ? unit.getName() : "N/A") +
                ", quantity=" + quantity +
                '}';
    }
}



