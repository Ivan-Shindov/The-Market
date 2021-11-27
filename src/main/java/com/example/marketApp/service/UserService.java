package com.example.marketApp.service;

import com.example.marketApp.model.dto.PostUserDto;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.dto.ViewUserDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;

public interface UserService {

    ViewUserDTO getUserWithItemsByUserId(Long id);

    UserEntity addUser(PostUserDto postUserDto);

    ItemProjectionDTO getUserById(Long id);
}
