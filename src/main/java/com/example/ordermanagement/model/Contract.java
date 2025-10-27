package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Contract {
    private String id;
    private String name;
    private ContractType contractTypeID;
    private String status;
    private List<ContractLine> contractLines = new ArrayList<>();

    public Contract(String id, String name, ContractType contractTypeID, String status) {
        this.id = id;
        this.name = name;
        this.contractTypeID = contractTypeID;
        this.status = status;
    }

    public  Contract() {}

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

    public ContractType getContractTypeID() {
        return contractTypeID;
    }

    public void setContractTypeID(ContractType contractTypeID) {
        this.contractTypeID = contractTypeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    public void addContractLines(ContractLine line) {
        this.contractLines.add(line);
    }

    public int getNumberOfContractLines() {
        return contractLines.size();
    }
    @Override
    public String toString() {
        String contractTypeName;
        if (contractTypeID != null) {
            contractTypeName = contractTypeID.getName();
        } else {
            contractTypeName = "N/A";
        }

        return "Contract{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + contractTypeName +
                ", status='" + status + '\'' +
                ", contractLines=" + contractLines.size() +
                '}';
    }
}
