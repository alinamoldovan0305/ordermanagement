package com.example.ordermanagement.service;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.repository.ContractLineRepository;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.SellableItemRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractLineService {

    private final ContractLineRepository contractLineRepository;
    private final ContractRepository contractRepository;
    private final SellableItemRepository itemRepository;
    private final UnitsOfMeasureRepository unitRepository;

    public ContractLineService(
            ContractLineRepository contractLineRepository,
            ContractRepository contractRepository,
            SellableItemRepository itemRepository,
            UnitsOfMeasureRepository unitRepository
    ) {
        this.contractLineRepository = contractLineRepository;
        this.contractRepository = contractRepository;
        this.itemRepository = itemRepository;
        this.unitRepository = unitRepository;
    }


    public List<ContractLine> getAll() {
        return contractLineRepository.findAll();
    }

    public ContractLine getById(Long id) {
        return contractLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit linia de contract cu id: " + id));
    }


    public ContractLine save(ContractLine contractLine) {
        validateContractLine(contractLine);
        return contractLineRepository.save(contractLine);
    }


    public ContractLine update(Long id, ContractLine updatedLine) {
        ContractLine existing = contractLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit linia de contract cu id: " + id));

        if (!existing.getContract().getId().equals(updatedLine.getContract().getId())) {
            throw new IllegalArgumentException("Nu se poate schimba contractul unei liniii existente!");
        }

        validateContractLine(updatedLine);

        existing.setItem(updatedLine.getItem());
        existing.setUnit(updatedLine.getUnit());
        existing.setQuantity(updatedLine.getQuantity());

        return contractLineRepository.save(existing);
    }


    public void delete(Long id) {
        if (!contractLineRepository.existsById(id)) {
            throw new EntityNotFoundException("Nu s-a gasit linia de contract cu id: " + id);
        }
        contractLineRepository.deleteById(id);
    }


    private void validateContractLine(ContractLine line) {

        // Contract valid
        if (line.getContract() == null || line.getContract().getId() == null ||
                !contractRepository.existsById(line.getContract().getId())) {
            throw new IllegalArgumentException("Contractul nu exista!");
        }

        // Contract activ
        Contract contract = contractRepository.findById(line.getContract().getId()).orElseThrow();
        if (contract.getStatus() == ContractStatus.DOWN) {
            throw new IllegalArgumentException("Nu se pot adauga linii unui contract inactiv!");
        }

        // Item valid
        if (line.getItem() == null || line.getItem().getId() == null ||
                !itemRepository.existsById(line.getItem().getId())) {
            throw new IllegalArgumentException("Obiectul nu exista!");
        }

        // Unit validă
        if (line.getUnit() == null || line.getUnit().getId() == null ||
                !unitRepository.existsById(line.getUnit().getId())) {
            throw new IllegalArgumentException("Unitatea nu exista!");
        }

        // Quantity > 0
        if (line.getQuantity() <= 0) {
            throw new IllegalArgumentException("Cantitatea trebuie sa fie mai mare de 0!");
        }

        // Item unic în contract la creare
        if (line.getId() == null && contractLineRepository.existsByContractIdAndItemId(
                line.getContract().getId(),
                line.getItem().getId()
        )) {
            throw new IllegalArgumentException("Obiectul exista deja in contract!");
        }
    }
}
