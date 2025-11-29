//package com.example.ordermanagement.model;
//
//import com.example.ordermanagement.enums.ContractStatus;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Contract {
//    private String id;
//    private String name;
//    private String contractTypeID;
//    private ContractStatus status;
//    //private List<ContractLine> contractLines = new ArrayList<>();
//
//    public Contract(String id, String name, String contractTypeID, ContractStatus status) {
//        this.id = id;
//        this.name = name;
//        this.contractTypeID = contractTypeID;
//        this.status = status;
//    }
//
//    public  Contract() {}
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
//    public String getContractTypeID() {
//        return contractTypeID;
//    }
//
//    public void setContractTypeID(String contractTypeID) {
//        this.contractTypeID = contractTypeID;
//    }
//
//    public ContractStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(ContractStatus status) {
//        this.status = status;
//    }
//
////    public List<ContractLine> getContractLines() {
////        return contractLines;
////    }
////
////    public void addContractLines(ContractLine line) {
////        this.contractLines.add(line);
////    }
////
////    public int getNumberOfContractLines() {
////        return contractLines.size();
////    }
//    @Override
//    public String toString() {
//        String statusValue;
//
//        if (status != null) {
//            statusValue = status.toString();
//        } else {
//            statusValue = "N/A";
//        }
//
//        return "Contract{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", type=" + contractTypeID +
//                ", status=" + statusValue +
////                ", contractLines=" + contractLines.size() +
//                '}';
//    }
//
//}

package com.example.ordermanagement.model;

import com.example.ordermanagement.enums.ContractStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract name is mandatory")
    @Column(nullable = false)
    private String name;

    // RELAȚIA M:1 CU Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is required")
    private Customer customer;

    // RELAȚIA M:1 CU ContractType
    @ManyToOne
    @JoinColumn(name = "contract_type_id", nullable = false)
    @NotNull(message = "Contract type is required")
    private ContractType contractType;

    // ENUM ContractStatus (ACTIVE, INACTIVE, DRAFT etc.)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus status;

    // RELAȚIA 1:N CU ContractLine
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractLine> contractLines = new ArrayList<>();

    // Constructori
    public Contract() {
    }

    public Contract(String name, Customer customer, ContractType contractType, ContractStatus status) {
        this.name = name;
        this.customer = customer;
        this.contractType = contractType;
        this.status = status;
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

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    public void setContractLines(List<ContractLine> contractLines) {
        this.contractLines = contractLines;
    }

    // Helper methods pentru bidirecționalitate
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
                ", status=" + (status != null ? status : "N/A") +
                ", contractLines=" + contractLines.size() +
                '}';
    }
}


