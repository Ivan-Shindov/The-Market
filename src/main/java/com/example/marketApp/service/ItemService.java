package com.example.marketApp.service;

import com.example.marketApp.model.dto.PostItemDto;
import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;

import java.util.List;

public interface ItemService {
    ItemEntity getItemById(Long id);

    ViewItemDto createItem(PostItemDto postItemDto);

    List<ItemEntity> getAllWithOwnerId(Long ownerId);
}
