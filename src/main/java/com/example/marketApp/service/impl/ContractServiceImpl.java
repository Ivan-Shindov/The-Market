package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.PostContractDto;
import com.example.marketApp.model.dto.ViewContractDto;
import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;
import com.example.marketApp.repository.ContractRepository;
import com.example.marketApp.repository.ItemRepository;
import com.example.marketApp.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public ContractServiceImpl(ContractRepository contractRepository,
                               ItemRepository itemRepository, ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ViewContractDto createContract(PostContractDto contractDto) {

        Long itemId = contractDto.getItemId();

        // TODO fix findById to be with query
        ItemEntity item = this.itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("No such item with id: " + itemId + " in Database."));

        ContractEntity contractEntity = new ContractEntity();
            contractEntity
                    .setSeller(item.getOwner())
                    .setItem(item)
                    .setPrice(contractDto.getPrice())
                    .setActive(true);

            this.contractRepository.save(contractEntity);

        return modelMapper.map(contractEntity, ViewContractDto.class);
    }

    @Override
    public ContractInfoProjectionDTO getContractById(Long id) {

        ContractInfoProjectionDTO contractInfo = this.contractRepository.getContractById(id);

        if (contractInfo.getId() == null) {
            System.out.println("EXCEPTION HERE ! NOT FOUND CONTRACT");
        }
        return contractInfo;
    }
}
