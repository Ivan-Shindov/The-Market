package com.example.marketApp.service;

import com.example.marketApp.model.dto.PostItemDto;
import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.projection.AllItemsProjectionDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;

import java.util.List;

public interface ItemService {
    ItemProjectionDTO getItemById(Long id);

    ViewItemDto createItem(PostItemDto postItemDto);

    List<AllItemsProjectionDTO> getAllWithOwnerId(Long ownerId);
}
