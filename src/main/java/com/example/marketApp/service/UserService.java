package com.example.marketApp.service;

import com.example.marketApp.model.projection.ViewProjectionUserDto;

public interface UserService {
    ViewProjectionUserDto getUserById(Long id);
}
