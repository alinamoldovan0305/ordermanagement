package com.example.ordermanagement.model;

import com.example.ordermanagement.enums.ContractStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract name is mandatory")
    @Size(min = 3, max = 100, message = "Contract name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is required")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "contract_type_id", nullable = false)
    @NotNull(message = "Contract type is required")
    private ContractType contractType;

    @NotNull(message = "Contract status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus status;

    @NotNull
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractLine> contractLines = new ArrayList<>();

    public Contract() {}

    public Contract(String name, Customer customer, ContractType contractType, ContractStatus status) {
        this.name = name;
        this.customer = customer;
        this.contractType = contractType;
        this.status = status;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public ContractType getContractType() { return contractType; }
    public void setContractType(ContractType contractType) { this.contractType = contractType; }

    public ContractStatus getStatus() { return status; }
    public void setStatus(ContractStatus status) { this.status = status; }

    public List<ContractLine> getContractLines() { return contractLines; }
    public void setContractLines(List<ContractLine> contractLines) { this.contractLines = contractLines; }

    // Helper methods bidirecționale
    public void addContractLine(ContractLine line) {
        contractLines.add(line);
        line.setContract(this);
    }

    public void removeContractLine(ContractLine line) {
        contractLines.remove(line);
        line.setContract(null);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customer=" + (customer != null ? customer.getName() : "N/A") +
                ", contractType=" + (contractType != null ? contractType.getName() : "N/A") +
                ", status=" + status +
                ", contractLines=" + contractLines.size() +
                '}';
    }
}
