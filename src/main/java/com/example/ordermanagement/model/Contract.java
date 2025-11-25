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

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // înlocuit String -> Long pentru compatibilitate DB

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "contract_type_id", nullable = false)
    private String contractTypeID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus status;

    // Constructor fără parametri (obligatoriu pentru JPA)
    public Contract() {}

    public Contract(String name, String contractTypeID, ContractStatus status) {
        this.name = name;
        this.contractTypeID = contractTypeID;
        this.status = status;
    }

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

    public String getContractTypeID() {
        return contractTypeID;
    }

    public void setContractTypeID(String contractTypeID) {
        this.contractTypeID = contractTypeID;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + contractTypeID + '\'' +
                ", status=" + (status != null ? status : "N/A") +
                '}';
    }
}
