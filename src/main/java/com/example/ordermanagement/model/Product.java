package com.example.ordermanagement.model;

public class Product extends SellableItem{
    private double value;

    public Product(String id, String name, double value) {
        super(id, name);
        this.value = value;
    }

    public Product() {}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

