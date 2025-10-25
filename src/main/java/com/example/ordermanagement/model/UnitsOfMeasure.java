package com.example.ordermanagement.model;

public class UnitsOfMeasure {
    private String id;
    private String name;
    private String symbol;

    public UnitsOfMeasure(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public UnitsOfMeasure() {}

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

//    @Override
//    public String toString() {
//        return name + "(ID: " + id + ")" ;
    //}
}
