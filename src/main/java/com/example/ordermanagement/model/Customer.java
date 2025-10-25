package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String currency;
    private List<Order> orders = new ArrayList<>();
    private List<Contract> contracts =  new ArrayList<>();

    public Customer(String id, String name, String currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public Customer() {}

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrders(Order order) {
        this.orders.add(order);
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContracts(Contract contract) {
        this.contracts.add(contract);
    }
}
