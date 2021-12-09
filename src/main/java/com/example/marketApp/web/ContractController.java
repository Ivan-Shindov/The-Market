package com.example.marketApp.web;

import com.example.marketApp.model.dto.*;
import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.projection.ActiveContractInfoProjection;
import com.example.marketApp.model.projection.ContractInfoProjection;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;
import com.example.marketApp.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Transactional
    @PostMapping("/contract/create")
    public ResponseEntity<ViewContractDto> create(
            @RequestBody PostContractDto postContractDto,
            UriComponentsBuilder builder) {

        try {
            ViewContractDto contractDto = this.contractService.createContract(postContractDto);

            URI location = builder.path("/create/{id}").
                    buildAndExpand(contractDto.getId())
                    .toUri();

            return ResponseEntity.
                    created(location)
                    .body(contractDto);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @GetMapping("/contract/{id}")
    public ResponseEntity<ContractInfoProjectionDTO> getContract(@PathVariable Long id) {
        try {
            ContractInfoProjectionDTO contract = this.contractService.getContractById(id);
            return ResponseEntity.ok(contract);

        } catch (NullPointerException ex) {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/contract/{id}")
    public ResponseEntity<ContractInfoProjection> update(@RequestBody UpdateContractDTO updateContractDTO,
                                                         @PathVariable Long id) {
        Long updated = this.contractService.updateContract(updateContractDTO, id);

        return updated != null ?
                ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/contracts/all")
    public ResponseEntity<List<ActiveContractInfoProjection>> getAllActiveContracts() {

            List<ActiveContractInfoProjection> contracts = this.contractService.getAllActiveContracts();
            return ResponseEntity.ok(contracts);

    }

    @PutMapping("/close/contract/{id}")
    public ResponseEntity<ContractEntity> close(@PathVariable(name = "id") Long id,
                                                @RequestBody CloseContractDTO contractDTO) {

        Long contractId = this.contractService.closingContract(id, contractDTO);

        return contractId != null ?
                ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

// I could not make it like in document,sorry about that. Just made it to work.
    @GetMapping("/closed/contracts")
    public ResponseEntity<List<ViewContractClosedDTO>> getAllClosedContracts() {

        List<ViewContractClosedDTO> allClosedContracts = this.contractService.getAllClosedContracts();

        return ResponseEntity.ok(allClosedContracts);
    }


    @GetMapping("/all/contracts/seller/{sellerId}")
    public ResponseEntity< List<ContractInfoProjectionDTO>> getAllBySellerId(@PathVariable Long sellerId) {

        List<ContractInfoProjectionDTO> listBySeller = this.contractService.getAllBySellerId(sellerId);

        return ResponseEntity.ok(listBySeller);
    }
}
