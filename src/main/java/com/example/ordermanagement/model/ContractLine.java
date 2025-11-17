package com.example.ordermanagement.model;

public class ContractLine {

    private String id;
    private String itemId;   // <-- în loc de SellableItem item
    private String unitId;   // <-- în loc de UnitsOfMeasure unit
    private double quantity;

    public ContractLine() {}

    public ContractLine(String id, String itemId, String unitId, double quantity) {
        this.id = id;
        this.itemId = itemId;
        this.unitId = unitId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
