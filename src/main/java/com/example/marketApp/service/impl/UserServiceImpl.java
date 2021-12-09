package com.example.marketApp.service.impl;

import com.example.marketApp.model.dto.PostUserDto;
import com.example.marketApp.model.dto.ViewUserItemsDTO;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.dto.ViewUserDTO;
import com.example.marketApp.model.projection.UserWithoutItemsProjection;
import com.example.marketApp.repository.UserRepository;
import com.example.marketApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ViewUserDTO getUserWithItemsByUserId(Long id) {

        UserEntity userEntity = this.userRepository.findOwner(id)
                .orElseThrow(() -> new IllegalArgumentException("No such user with id " + id));
        ;

        List<ViewUserItemsDTO> items = userEntity.getItems()
                .stream()
                .map(item -> {
                    ViewUserItemsDTO viewUserItemsDTO = new ViewUserItemsDTO();
                    viewUserItemsDTO.setId(item.getId())
                            .setName(item.getName());
                    return viewUserItemsDTO;
                })
                .collect(Collectors.toList());

        return new ViewUserDTO(
                userEntity.getId(),
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
            if (this.userRepository.existsById(postUserDto.getId())) {
                throw new IllegalArgumentException("There is already user with id: " + postUserDto.getId());
            }
            UserEntity entity = new UserEntity().setId(postUserDto.getId())
                    .setUsername(postUserDto.getUsername())
                    .setAccount(postUserDto.getAccount());
            UserEntity savedEntity = this.userRepository.save(entity);

            return savedEntity;

        } else {
            throw new IllegalArgumentException("There is already user with this username: "
                    + postUserDto.getUsername());
        }

    }

    @Override
    public UserWithoutItemsProjection getUserById(Long id) {

        UserWithoutItemsProjection userView = this.userRepository.getUserById(id);

        return userView;
    }
}
