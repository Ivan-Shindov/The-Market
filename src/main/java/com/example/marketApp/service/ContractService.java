package com.example.marketApp.service;

import com.example.marketApp.model.dto.PostContractDto;
import com.example.marketApp.model.dto.ViewContractDto;

public interface ContractService {

    ViewContractDto createContract(PostContractDto postContractDto);

}
