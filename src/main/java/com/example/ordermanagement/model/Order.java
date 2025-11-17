//package com.example.ordermanagement.model;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Order {
//    private String id;
//    private String name;
//    private Customer customer;
//    private Contract contract;
//    //private List<OrderLine> orderLines = new ArrayList<>();
//    private LocalDate orderDate;
//    private boolean delivered;
//
//    public Order(String id, String name, Customer customer, Contract contract, LocalDate orderDate, boolean delivered) {
//        this.id = id;
//        this.name = name;
//        this.customer = customer;
//        this.contract = contract;
//        this.orderDate = orderDate;
//        this.delivered = delivered;
//    }
//
//    public Order() {}
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }
//
////    public List<OrderLine> getOrderLines() {
////        return orderLines;
////    }
////
////    public void addOrderLine(OrderLine line) {
////        this.orderLines.add(line);
////    }
//
//    public LocalDate getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDate orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public boolean isDelivered() {
//        return delivered;
//    }
//
//    public void setDelivered(boolean delivered) {
//        this.delivered = delivered;
//    }
//
//    @Override
//    public String toString() {
//        String customerName;
//        String contractName;
//        String orderDateValue;
//
//        if (customer != null) {
//            customerName = customer.getName();
//        } else {
//            customerName = "N/A";
//        }
//
//        if (contract != null) {
//            contractName = contract.getName();
//        } else {
//            contractName = "N/A";
//        }
//
//        if (orderDate != null) {
//            orderDateValue = orderDate.toString();
//        } else {
//            orderDateValue = "N/A";
//        }
//
//        return "Order{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", orderDate=" + orderDateValue +
//                ", delivered=" + delivered +
//                ", customer='" + customerName + '\'' +
//                ", contract='" + contractName + '\'' +
//                '}';
//    }
//}
package com.example.ordermanagement.model;

import java.time.LocalDate;

public class Order {

    private String id;
    private String name;

    // IMPORTANT: doar ID-urile, nu obiectele!
    private String customerId;
    private String contractId;

    private LocalDate orderDate;
    private boolean delivered;

    public Order() {}

    public Order(String id, String name, String customerId, String contractId,
                 LocalDate orderDate, boolean delivered) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.contractId = contractId;
        this.orderDate = orderDate;
        this.delivered = delivered;
    }

    // GETTERS + SETTERS

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
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
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", contractId='" + contractId + '\'' +
                ", orderDate=" + orderDate +
                ", delivered=" + delivered +
                '}';
    }
}
