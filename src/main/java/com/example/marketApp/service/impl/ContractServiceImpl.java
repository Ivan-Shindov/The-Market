package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.*;
import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.projection.ActiveContractInfoProjection;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
import com.example.marketApp.repository.ContractRepository;
import com.example.marketApp.repository.ItemRepository;
import com.example.marketApp.repository.UserRepository;
import com.example.marketApp.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ContractServiceImpl(ContractRepository contractRepository,
                               ItemRepository itemRepository, UserRepository userRepository,
                               ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ViewContractDto createContract(PostContractDto contractDto) {

        Long itemId = contractDto.getItemId();

        ItemEntity item = this.itemRepository.findItemById(itemId)
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

    @Override
    public Long updateContract(UpdateContractDTO contractDTO, Long id) {

        Long itemId = contractDTO.getItemId();
        ItemProjectionDTO itemProjectionDTO = this.itemRepository.findByItemId(itemId);

        if (itemProjectionDTO == null) {
            throw new IllegalArgumentException("Not found item with ID: " + itemId);
        }
        ContractEntity contractEntity = this.contractRepository.findContractById(id);
        if (contractEntity == null) {
            throw new IllegalArgumentException("Not found contract with ID: " + id);
        }

        Integer updatedId = this.contractRepository.updateActiveContractWithItemId(contractDTO.getPrice(), id, itemId);


        return updatedId.longValue();
    }

    @Override
    public List<ActiveContractInfoProjection> getAllActiveContracts() {
        List<ActiveContractInfoProjection> activeContracts = this.contractRepository.getAllWithActiveTrue();

        for (ActiveContractInfoProjection activeContract : activeContracts) {
            System.out.println(activeContract);
        }

        return activeContracts;
    }

    @Override
    public Long closingContract(Long id, CloseContractDTO closeContractDTO) {
        Long itemId = closeContractDTO.getItemId();
        Long buyerId = closeContractDTO.getBuyerId();

        ContractEntity contract = this.contractRepository.findContractById(id);
        if (contract == null) {
            throw new IllegalArgumentException("Contract does not exist in DB");
        }

        ItemEntity item = this.itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("Item with ID: " + itemId + " not found."));

        UserEntity seller = this.userRepository.findById(item.getOwner().getId())
                .orElseThrow(() -> new IllegalArgumentException("Seller does not exist in DB"));

        UserEntity buyerEntity = this.userRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer does not exist in DB"));


        BigDecimal contractPrice = contract.getPrice();
        int compared = buyerEntity.getAccount().compareTo(contractPrice);

        if (compared < 0) {
            throw new IllegalStateException("Buyer does not have enough money in his account to buy a item.");
        }

        BigDecimal sellerAccount = seller.getAccount();

        if (sellerAccount.subtract(contractPrice).compareTo(BigDecimal.ZERO) >= 0) {
            seller.setAccount(sellerAccount.add(contractPrice));

            BigDecimal buyerAccount = buyerEntity.getAccount();
            BigDecimal subtractSum = buyerAccount.subtract(contractPrice);

            if (subtractSum.compareTo(BigDecimal.ZERO) < 0) {
                buyerEntity.setAccount(BigDecimal.ZERO);
            } else {
                buyerEntity.setAccount(subtractSum);
            }
        } else {
            seller.setAccount(BigDecimal.ZERO);
        }

        this.itemRepository.changeItemOwner(itemId,buyerId);

        contract.setActive(false);

        this.userRepository.saveAll(List.of(seller, buyerEntity));
        return this.contractRepository.save(contract).getId();
    }

    @Override
    public List<ViewContractClosedDTO> getAllClosedContracts() {

        List<ContractEntity> aLlClosed = this.contractRepository.getALlClosed();

        List<ViewContractClosedDTO> closedDTOList = aLlClosed.stream()
                .map(contractEntity -> {
                    ViewContractClosedDTO dto = new ViewContractClosedDTO();
                    dto
                            .setBuyerId(contractEntity.getBuyer().getId())
                            .setBuyerUsername(contractEntity.getBuyer().getUsername())
                            .setItemId(contractEntity.getItem().getId())
                            .setSellerId(contractEntity.getSeller().getId())
                            .setSellerUsername(contractEntity.getSeller().getUsername())
                            .setPrice(contractEntity.getPrice())
                            .setActive(contractEntity.isActive());
                    return dto;
                }).collect(Collectors.toList());

        return closedDTOList;
    }

    @Override
    public  List<ContractInfoProjectionDTO> getAllBySellerId(Long sellerId) {

        List<ContractInfoProjectionDTO> listBySeller = this.contractRepository.findAllBySellerId(sellerId);

        return listBySeller;
    }
}
