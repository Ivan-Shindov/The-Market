package com.example.marketApp.web;

import com.example.marketApp.model.dto.PostUserDto;
import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.dto.ViewUserDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
import com.example.marketApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}/items")
    public ResponseEntity<ViewUserDTO> getUserWithItems(@PathVariable Long id) {

        try {
            ViewUserDTO view = this.userService.getUserWithItemsByUserId(id);
            return ResponseEntity.ok(view);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ItemProjectionDTO> getUser(@PathVariable Long id) {

        ItemProjectionDTO view = this.userService.getUserById(id);

        return ResponseEntity.ok(view);
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> addUser(@RequestBody PostUserDto postUserDto,
                                              UriComponentsBuilder builder) {

        UserEntity userEntity = this.userService.addUser(postUserDto);

        URI uriLocation = builder.path("/users/{id}")
                .buildAndExpand(userEntity.getId())
                .toUri();

        return ResponseEntity
                .created(uriLocation)
                .body(userEntity);
    }

}
