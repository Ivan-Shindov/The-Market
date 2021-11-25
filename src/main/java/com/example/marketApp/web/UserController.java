package com.example.marketApp.web;

import com.example.marketApp.model.projection.ViewProjectionUserDto;
import com.example.marketApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ViewProjectionUserDto> getUser(@PathVariable Long id) {

        ViewProjectionUserDto view = this.userService.getUserById(id);

        return ResponseEntity.ok(view);
    }

    //TODO post mapping
}
