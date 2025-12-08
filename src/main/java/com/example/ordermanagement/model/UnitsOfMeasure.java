
package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "units_of_measure")
public class UnitsOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Unit name is required")
    @Size(max = 50, message = "Unit name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Unit symbol is required")
    @Size(max = 10, message = "Unit symbol cannot exceed 10 characters")
    private String symbol;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractLine> contractLines = new ArrayList<>();

    public UnitsOfMeasure() {}

    public UnitsOfMeasure(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) {
        this.symbol = symbol != null ? symbol.trim() : null;
    }

    public List<OrderLine> getOrderLines() { return orderLines; }
    public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }

    public List<ContractLine> getContractLines() { return contractLines; }
    public void setContractLines(List<ContractLine> contractLines) { this.contractLines = contractLines; }

    @Override
    public String toString() {
        return "UnitsOfMeasure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}

