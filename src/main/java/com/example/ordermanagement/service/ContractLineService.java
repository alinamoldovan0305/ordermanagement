//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.ContractLine;
//import com.example.ordermanagement.repository.ContractLineRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ContractLineService extends GenericService<ContractLine> {
//    public ContractLineService(ContractLineRepository repository) {
//        super(repository);
//    }
//
//}
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.ContractLineRepository;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractLineService {

    private final ContractLineRepository contractLineRepository;
    private final ContractRepository contractRepository;
    private final UnitsOfMeasureRepository unitRepository;

    public ContractLineService(ContractLineRepository contractLineRepository,
                               ContractRepository contractRepository,
                               UnitsOfMeasureRepository unitRepository) {
        this.contractLineRepository = contractLineRepository;
        this.contractRepository = contractRepository;
        this.unitRepository = unitRepository;
    }

    // --------------------- GET ALL ---------------------
    public List<ContractLine> getAll() {
        return contractLineRepository.findAll();
    }

    // --------------------- GET BY ID ---------------------
    public ContractLine getById(Long id) {
        return contractLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ContractLine not found with id: " + id));
    }

    // --------------------- CREATE ---------------------
    public ContractLine save(ContractLine contractLine) {
        validateContractLineRelations(contractLine);
        return contractLineRepository.save(contractLine);
    }

    // --------------------- UPDATE ---------------------
    public ContractLine update(Long id, ContractLine updatedLine) {
        ContractLine existing = contractLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ContractLine not found with id: " + id));

        validateContractLineRelations(updatedLine);

        existing.setContract(updatedLine.getContract());
        existing.setItem(updatedLine.getItem());
        existing.setUnit(updatedLine.getUnit());
        existing.setQuantity(updatedLine.getQuantity());

        return contractLineRepository.save(existing);
    }

    // --------------------- DELETE ---------------------
    public void delete(Long id) {
        if (!contractLineRepository.existsById(id)) {
            throw new EntityNotFoundException("ContractLine not found with id: " + id);
        }
        contractLineRepository.deleteById(id);
    }

    // --------------------- Helper Methods ---------------------
    private void validateContractLineRelations(ContractLine line) {
        if (line.getContract() == null || line.getContract().getId() == null ||
                !contractRepository.existsById(line.getContract().getId())) {
            throw new IllegalArgumentException("Contract does not exist!");
        }

        if (line.getUnit() == null || line.getUnit().getId() == null ||
                !unitRepository.existsById(line.getUnit().getId())) {
            throw new IllegalArgumentException("Unit of measure does not exist!");
        }
    }
}
