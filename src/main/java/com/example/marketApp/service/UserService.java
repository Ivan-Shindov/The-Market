package com.example.marketApp.service;

import com.example.marketApp.model.dto.PostUserDto;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.dto.ViewUserDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
import com.example.marketApp.model.projection.UserWithoutItemsProjection;

public interface UserService {

    ViewUserDTO getUserWithItemsByUserId(Long id);

    UserEntity addUser(PostUserDto postUserDto);

    UserWithoutItemsProjection getUserById(Long id);
}
