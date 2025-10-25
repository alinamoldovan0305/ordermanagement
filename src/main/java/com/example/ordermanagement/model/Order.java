package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String name;
    private Customer customer;
    private Contract contract;
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order(String id, String name, Customer customer, Contract contract) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.contract = contract;
    }

    public Order() {}

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLines(OrderLine ine) {
        this.orderLines.add(ine);
    }
}
