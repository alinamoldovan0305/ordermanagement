package com.example.ordermanagement.model;

public class OrderLine {
    private String id;
    private SellableItem item;
    private UnitsOfMeasure unit;
    private double quantity;

    public OrderLine(String id, SellableItem item, UnitsOfMeasure unit, double quantity) {
        this.id = id;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public OrderLine() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SellableItem getItem() {
        return item;
    }

    public void setItem(SellableItem item) {
        this.item = item;
    }

    public UnitsOfMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitsOfMeasure unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        String itemName;
        String unitSymbol;

        if (item != null) {
            itemName = item.getName();
        } else {
            itemName = "N/A";
        }

        if (unit != null) {
            unitSymbol = unit.getSymbol();
        } else {
            unitSymbol = "N/A";
        }

        return "OrderLine{" +
                "id='" + id + '\'' +
                ", item='" + itemName + '\'' +
                ", unit='" + unitSymbol + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
