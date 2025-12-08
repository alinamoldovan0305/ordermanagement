package com.example.ordermanagement.service;

import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.model.Contract;
import com.example.ordermanagement.repository.ContractRepository;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.ContractTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final ContractTypeRepository contractTypeRepository;

    public ContractService(ContractRepository contractRepository,
                           CustomerRepository customerRepository,
                           ContractTypeRepository contractTypeRepository) {
        this.contractRepository = contractRepository;
        this.customerRepository = customerRepository;
        this.contractTypeRepository = contractTypeRepository;
    }

    // --------------------- GET ALL ---------------------
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    // --------------------- GET BY ID ---------------------
    public Contract getById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found with id: " + id));
    }

    // --------------------- CREATE ---------------------
    public Contract save(Contract contract) {
        normalize(contract);
        validateContractRelations(contract);
        validateBusinessRulesOnCreate(contract);
        return contractRepository.save(contract);
    }

    // --------------------- UPDATE ---------------------
    public Contract update(Contract updatedContract) {

        if (updatedContract.getId() == null) {
            throw new IllegalArgumentException("Contract ID must not be null");
        }

        Contract existing = getById(updatedContract.getId());

        normalize(updatedContract);
        validateContractRelations(updatedContract);
        validateBusinessRulesOnUpdate(existing, updatedContract);

        existing.setName(updatedContract.getName());
        existing.setCustomer(updatedContract.getCustomer());
        existing.setContractType(updatedContract.getContractType());
        existing.setStatus(updatedContract.getStatus());

        return contractRepository.save(existing);
    }

    // --------------------- DELETE ---------------------
    public void delete(Long id) {
        Contract contract = getById(id);

        // BUSINESS RULE: NU ștergi contract dacă are linii
        if (!contract.getContractLines().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete a contract that contains lines!");
        }

        contractRepository.deleteById(id);
    }

    // --------------------- VALIDĂRI ---------------------

    private void validateContractRelations(Contract contract) {
        if (contract.getCustomer() == null ||
                contract.getCustomer().getId() == null ||
                !customerRepository.existsById(contract.getCustomer().getId())) {
            throw new IllegalArgumentException("Customer does not exist!");
        }

        if (contract.getContractType() == null ||
                contract.getContractType().getId() == null ||
                !contractTypeRepository.existsById(contract.getContractType().getId())) {
            throw new IllegalArgumentException("Contract type does not exist!");
        }
    }

    private void validateBusinessRulesOnCreate(Contract contract) {

        // Exemplu: nu creezi direct contract INACTIVE
        if (contract.getStatus() == ContractStatus.DOWN) {
            throw new IllegalArgumentException("A new contract cannot start as INACTIVE");
        }

        // Exemplu: nume unic per client
        if (contractRepository.existsByNameAndCustomerId(contract.getName(), contract.getCustomer().getId())) {
            throw new IllegalArgumentException("This customer already has a contract with this name!");
        }
    }

    private void validateBusinessRulesOnUpdate(Contract existing, Contract updated) {

        // Nu schimbi clientul dacă există linii
        if (!existing.getCustomer().getId().equals(updated.getCustomer().getId()) &&
                !existing.getContractLines().isEmpty()) {
            throw new IllegalArgumentException("Cannot change the customer because the contract already has lines.");
        }

        // Nu dezactivezi contractul dacă are linii
        if (updated.getStatus() == ContractStatus.DOWN &&
                !existing.getContractLines().isEmpty()) {
            throw new IllegalArgumentException("Cannot deactivate a contract that contains lines.");
        }
    }

    // --------------------- NORMALIZARE ---------------------
    private void normalize(Contract contract) {
        if (contract.getName() != null) {
            contract.setName(contract.getName().trim());
        }
    }
}
