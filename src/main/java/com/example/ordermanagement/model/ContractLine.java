package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "contract_lines")
public class ContractLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELAȚIA M:1 CU Contract
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    @NotNull(message = "Contract is required")
    private Contract contract;

    // RELAȚIA M:1 CU SellableItem
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @NotNull(message = "Item is required")
    private SellableItem item;

    // RELAȚIA M:1 CU UnitOfMeasure
    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    @NotNull(message = "Unit is required")
    private UnitsOfMeasure unit;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private double quantity;

    public ContractLine() {}

    public ContractLine(Contract contract, SellableItem item, UnitsOfMeasure unit, double quantity) {
        this.contract = contract;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public SellableItem getItem() { return item; }
    public void setItem(SellableItem item) { this.item = item; }

    public UnitsOfMeasure getUnit() { return unit; }
    public void setUnit(UnitsOfMeasure unit) { this.unit = unit; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ContractLine{" +
                "id=" + id +
                ", contractId=" + (contract != null ? contract.getId() : "N/A") +
                ", item=" + (item != null ? item.getName() : "N/A") +
                ", unit=" + (unit != null ? unit.getName() : "N/A") +
                ", quantity=" + quantity +
                '}';
    }
}
