package com.example.ordermanagement.model;

public class Product extends SellableItem{
    private double value;
    private String category;
    private int stock;

    public Product(String id, String name, double value, String category, int stock) {
        super(id, name);
        this.value = value;
        this.category = category;
        this.stock = stock;
    }

    public Product() {}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public boolean isInStock() {
        return stock > 0;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                '}';
    }
}
