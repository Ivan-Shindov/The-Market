package com.example.marketApp.service.impl;

import com.example.marketApp.model.projection.ViewProjectionUserDto;
import com.example.marketApp.repository.UserRepository;
import com.example.marketApp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ViewProjectionUserDto getUserById(Long id) {

        ViewProjectionUserDto userView = this.userRepository.getUserById(id);

        return userView;
    }
}
