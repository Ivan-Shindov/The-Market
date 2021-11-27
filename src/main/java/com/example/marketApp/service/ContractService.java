package com.example.marketApp.service;

import com.example.marketApp.model.dto.PostContractDto;
import com.example.marketApp.model.dto.ViewContractDto;
import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;

public interface ContractService {

    ViewContractDto createContract(PostContractDto postContractDto);

    ContractInfoProjectionDTO getContractById(Long id);
}
