//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.Contract;
//import com.example.ordermanagement.repository.ContractRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ContractService extends GenericService<Contract> {
//    public ContractService(ContractRepository repository) {
//        super(repository);
//    }
//}
package com.example.ordermanagement.service;

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
        validateContractRelations(contract);
        return contractRepository.save(contract);
    }

    // --------------------- UPDATE ---------------------
    public Contract update(Contract updatedContract) {
        if (updatedContract.getId() == null) {
            throw new IllegalArgumentException("Contract ID must not be null");
        }

        Contract existing = contractRepository.findById(updatedContract.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Contract not found with id: " + updatedContract.getId()));

        // Validăm relațiile
        validateContractRelations(updatedContract);

        existing.setName(updatedContract.getName());
        existing.setCustomer(updatedContract.getCustomer());
        existing.setContractType(updatedContract.getContractType());

        return contractRepository.save(existing);
    }


    // --------------------- DELETE ---------------------
    public void delete(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new EntityNotFoundException("Contract not found with id: " + id);
        }
        contractRepository.deleteById(id);
    }

    // --------------------- Helper Methods ---------------------
    private void validateContractRelations(Contract contract) {
        if (contract.getCustomer() == null || contract.getCustomer().getId() == null ||
                !customerRepository.existsById(contract.getCustomer().getId())) {
            throw new IllegalArgumentException("Customer does not exist!");
        }

        if (contract.getContractType() == null || contract.getContractType().getId() == null ||
                !contractTypeRepository.existsById(contract.getContractType().getId())) {
            throw new IllegalArgumentException("Contract type does not exist!");
        }
    }
}


