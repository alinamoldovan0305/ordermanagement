
package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract_types")
public class ContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract type name is required")
    @Column(nullable = false, unique = true)
    private String name;

    // OPTIONAL: Relație cu Contract
    // Dacă vrei să vezi toate contractele de un anumit tip.
    @OneToMany(mappedBy = "contractType", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Contract> contracts = new ArrayList<>();

    public ContractType() {}

    public ContractType(String name) {
        this.name = name;
    }

    // Getters & Setters
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

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contracts=" + contracts.size() +
                '}';
    }

}


