package com.example.marketApp.web;

import com.example.marketApp.model.dto.PostContractDto;
import com.example.marketApp.model.dto.ViewContractDto;
import com.example.marketApp.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class ContractController {

    private final ContractService contractService;


    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


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
}
