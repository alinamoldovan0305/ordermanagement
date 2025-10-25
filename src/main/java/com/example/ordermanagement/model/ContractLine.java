package com.example.ordermanagement.model;

import org.springframework.format.annotation.DurationFormat;

public class ContractLine {
    private String id;
    private SellableItem item;
    private UnitsOfMeasure unit;
    private double quantity;

    public ContractLine(String id, SellableItem item, UnitsOfMeasure unit, double quantity) {
        this.id = id;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public ContractLine() {}

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
}

