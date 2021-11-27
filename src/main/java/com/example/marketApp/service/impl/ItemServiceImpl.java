package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.PostItemDto;
import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.projection.AllItemsProjectionDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
import com.example.marketApp.repository.ItemRepository;
import com.example.marketApp.repository.UserRepository;
import com.example.marketApp.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemProjectionDTO getItemById(Long id) {
        ItemProjectionDTO itemView = this.itemRepository.findByItemId(id);

        return itemView;
    }

    @Override
    public ViewItemDto createItem(PostItemDto postItemDto) {

        UserEntity userEntity = this.userRepository
                .findOwner(postItemDto.getOwnerId())
                .orElseThrow(() ->
                        new IllegalArgumentException("User with id: " + postItemDto.getOwnerId() +
                                " not exist."));

        ItemEntity newItemEntity = new ItemEntity()
                .setName(postItemDto.getName())
                .setOwner(userEntity)
                .setId(postItemDto.getId());

        ItemEntity savedItemEntity = this.itemRepository.save(newItemEntity);

        userEntity.addItem(newItemEntity);

        return new ViewItemDto()
                .setId(savedItemEntity.getId())
                .setName(savedItemEntity.getName())
                .setOwnerUsername(userEntity.getUsername());

    }

    @Override
    public List<AllItemsProjectionDTO> getAllWithOwnerId(Long ownerId) {
//        List<ItemEntity> itemEntities = this.itemRepository.findAllByOwnerId(ownerId);

        List<AllItemsProjectionDTO> itemsByOwner = this.itemRepository.findAllItemsByOwnerId(ownerId);

        return itemsByOwner;
    }
}
