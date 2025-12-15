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

    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    public Contract getById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nu s-a gasit contractul cu id: " + id));
    }

    public Contract save(Contract contract) {
        normalize(contract);
        validateContractRelations(contract);
        validateBusinessRulesOnCreate(contract);
        return contractRepository.save(contract);
    }


    public Contract update(Contract updatedContract) {

        if (updatedContract.getId() == null) {
            throw new IllegalArgumentException("ID-ul de contract nu poate fi nul.");
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

    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contractul nu a fost gasit."));

        if (contract.getStatus() == ContractStatus.ACTIVE) {
            throw new IllegalArgumentException("Nu se poate sterge un contract activ!");
        }
        if (!contract.getContractLines().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nu se poate sterge un contract care contine linii."
            );
        }


        // DOWN contracts can be deleted; lines will be removed automatically by cascade/orphanRemoval
        contractRepository.delete(contract);
}



    private void validateContractRelations(Contract contract) {
        if (contract.getCustomer() == null ||
                contract.getCustomer().getId() == null ||
                !customerRepository.existsById(contract.getCustomer().getId())) {
            throw new IllegalArgumentException("Clientul nu exista!");
        }

        if (contract.getContractType() == null ||
                contract.getContractType().getId() == null ||
                !contractTypeRepository.existsById(contract.getContractType().getId())) {
            throw new IllegalArgumentException("Tipul de contract nu exista!");
        }
    }

    private void validateBusinessRulesOnCreate(Contract contract) {

        if (contract.getStatus() == ContractStatus.DOWN) {
            throw new IllegalArgumentException("Un nou contract nu poate incepe cu statusul INCATIVE");
        }

        if (contractRepository.existsByNameAndCustomerId(contract.getName(), contract.getCustomer().getId())) {
            throw new IllegalArgumentException("Clientul are deja un contract cu acest nume!");
        }
    }

    private void validateBusinessRulesOnUpdate(Contract existing, Contract updated) {

        if (!existing.getCustomer().getId().equals(updated.getCustomer().getId()) &&
                !existing.getContractLines().isEmpty()) {
            throw new IllegalArgumentException("Nu se poate modifica clientul deoarece contractul deja contine linii.");
        }

        if (updated.getStatus() == ContractStatus.DOWN &&
                !existing.getContractLines().isEmpty()) {
            throw new IllegalArgumentException("Nu se poate deactiva un contract care contine linii.");
        }
    }


    private void normalize(Contract contract) {
        if (contract.getName() != null) {
            contract.setName(contract.getName().trim());
        }
    }
}
