package com.example.ordermanagement.editor;

import java.beans.PropertyEditorSupport;

import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.service.ContractService;

public class ContractEditor extends PropertyEditorSupport {

    private final ContractService contractService;

    public ContractEditor(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    public void setAsText(String id) {
        if (id == null || id.isEmpty()) {
            setValue(null);
        } else {
            setValue(contractService.getById(Long.parseLong(id)));
        }
    }
}

