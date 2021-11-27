package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.PostUserDto;
import com.example.marketApp.model.dto.ViewUserItemsDTO;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.dto.ViewUserDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
import com.example.marketApp.repository.UserRepository;
import com.example.marketApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ViewUserDTO getUserWithItemsByUserId(Long id) {

        UserEntity userEntity = this.userRepository.findOwner(id)
                .orElseThrow(() -> new IllegalArgumentException("No such user with id " + id));
        ;

        List<ViewUserItemsDTO> items = userEntity.getItems()
                .stream()
                .map(item -> modelMapper.map(item, ViewUserItemsDTO.class))
                .collect(Collectors.toList());

        return new ViewUserDTO(userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getAccount(),
                items);
    }

    @Override
    public UserEntity addUser(PostUserDto postUserDto) {

        UserEntity userEntity =
                this.userRepository
                        .findByUsername(postUserDto.getUsername())
                        .orElse(null);

        if (userEntity == null) {
            userEntity = modelMapper.map(postUserDto, UserEntity.class);
        } else {
            // if there is already user with same username, just updates account value
            //userEntity.setAccount(postUserDto.getAccount());
            throw new RuntimeException("There is already user with this username: " + postUserDto.getUsername());
        }

        this.userRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public ItemProjectionDTO getUserById(Long id) {

        ItemProjectionDTO userView = this.userRepository.getUserById(id);

        return userView;
    }
}
