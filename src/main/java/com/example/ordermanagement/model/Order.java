package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Order name is required")
    private String name;

    // RELAȚIA M:1 CU Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is required")
    private Customer customer;

    // RELAȚIA M:1 CU Contract
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    @NotNull(message = "Contract is required")
    private Contract contract;

    // RELAȚIA 1:N CU OrderLine
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    private LocalDate orderDate;

    private boolean delivered;

    public Order() {}

    public Order(String name, Customer customer, Contract contract, LocalDate orderDate, boolean delivered) {
        this.name = name;
        this.customer = customer;
        this.contract = contract;
        this.orderDate = orderDate;
        this.delivered = delivered;
    }

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
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

    // HELPER METHODS PENTRU ORDER LINES
    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
        line.setOrder(this);
    }

    public void removeOrderLine(OrderLine line) {
        orderLines.remove(line);
        line.setOrder(null);
    }

    @Override
    public String toString() {
        String customerName = customer != null ? customer.getName() : "N/A";
        String contractName = contract != null ? contract.getName() : "N/A";
        String orderDateValue = orderDate != null ? orderDate.toString() : "N/A";

        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderDate=" + orderDateValue +
                ", delivered=" + delivered +
                ", customer='" + customerName + '\'' +
                ", contract='" + contractName + '\'' +
                ", orderLines=" + orderLines.size() +
                '}';
    }
}

