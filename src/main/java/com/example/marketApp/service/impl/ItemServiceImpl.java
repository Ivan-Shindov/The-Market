package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.PostItemDto;
import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.repository.ItemRepository;
import com.example.marketApp.repository.UserRepository;
import com.example.marketApp.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public ItemEntity getItemById(Long id) {
        return this.itemRepository.findByItemId(id)
                .orElseThrow(() -> new IllegalArgumentException("Object with id: " + id + " not exist in DB."));
    }

    @Override
    public ViewItemDto createItem(PostItemDto postItemDto) {

        UserEntity userEntity = this.userRepository
                .findById(postItemDto.getOwnerId())
                .orElseThrow(() ->
                        new IllegalArgumentException("User with id: " + postItemDto.getOwnerId() +
                                " not exist."));

        ItemEntity newItemEntity = new ItemEntity()
                .setName(postItemDto.getName())
                .setOwner(userEntity)
                .setId(postItemDto.getId());

        this.itemRepository.save(newItemEntity);

        return modelMapper.map(newItemEntity,ViewItemDto.class);
    }

    @Override
    public List<ItemEntity> getAllWithOwnerId(Long ownerId) {
        List<ItemEntity> itemEntities = this.itemRepository.findAllByOwnerId(ownerId);
        return itemEntities;
    }
}
