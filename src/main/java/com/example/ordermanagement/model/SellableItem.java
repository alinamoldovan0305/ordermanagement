package com.example.ordermanagement.model;

public class SellableItem {
    private String id;
    private String name;

    public SellableItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellableItem() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "(ID: " + id + ")" ;
    }
}

