package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String name;
    private Customer customer;
    private Contract contract;
    private List<OrderLine> orderLines = new ArrayList<>();

    private String orderDate;
    private boolean delivered;


    public Order(String id, String name, Customer customer, Contract contract, String orderDate, boolean delivered ) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.contract = contract;
        this.orderDate=" ";
        this.delivered = false;
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

    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public boolean isDelivered() {
        return delivered;
    }
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        String customerName;
        String contractName;

        if (customer != null) {
            customerName = customer.getName();
        } else {
            customerName = "N/A";
        }

        if (contract != null) {
            contractName = contract.getName();
        } else {
            contractName = "N/A";
        }

        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", delivered=" + delivered +
                ", customer='" + customerName + '\'' +
                ", contract='" + contractName + '\'' +
                ", orderLines=" + orderLines.size() +
                '}';
    }
}
