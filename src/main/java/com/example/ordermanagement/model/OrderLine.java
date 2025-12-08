package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELAȚIA M:1 CU Order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull(message = "Order is required")
    private Order order;

    // RELAȚIA M:1 CU SellableItem (produs sau serviciu)
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @NotNull(message = "Item is required")
    private SellableItem item;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    @NotNull(message = "Unit is required")
    private UnitsOfMeasure unit;

    @NotNull(message = "Quantity is required")
    private double quantity;

    public OrderLine() {}

    public OrderLine(Order order, SellableItem item, UnitsOfMeasure unit, double quantity) {
        this.order = order;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public SellableItem getItem() { return item; }
    public void setItem(SellableItem item) { this.item = item; }

    public UnitsOfMeasure getUnit() { return unit; }
    public void setUnit(UnitsOfMeasure unit) { this.unit = unit; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", orderId=" + (order != null ? order.getId() : "N/A") +
                ", item=" + (item != null ? item.getName() : "N/A") +
                ", unit=" + (unit != null ? unit.getName() : "N/A") +
                ", quantity=" + quantity +
                '}';
    }
}

