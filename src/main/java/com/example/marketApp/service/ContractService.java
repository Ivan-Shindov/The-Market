package com.example.marketApp.service;

import com.example.marketApp.model.dto.*;
import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.projection.ActiveContractInfoProjection;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;

import java.util.List;

public interface ContractService {

    ViewContractDto createContract(PostContractDto postContractDto);

    ContractInfoProjectionDTO getContractById(Long id);

    Long updateContract(UpdateContractDTO updateContractDTO, Long id);

    List<ActiveContractInfoProjection> getAllActiveContracts();

    Long closingContract(Long id,CloseContractDTO contractDTO);

    List<ViewContractClosedDTO> getAllClosedContracts();

    List<ContractInfoProjectionDTO> getAllBySellerId(Long sellerId);
}
