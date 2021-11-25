package com.example.marketApp.web;

import com.example.marketApp.model.dto.PostItemDto;
import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable Long id) {
        ItemEntity getItemDto = itemService.getItemById(id);


        return ResponseEntity.ok(getItemDto);
    }

    @PostMapping("/items")
    public ResponseEntity<ViewItemDto> postItem (@RequestBody PostItemDto postItemDto,
                                                 UriComponentsBuilder builder) {

        ViewItemDto viewItemDto = itemService.createItem(postItemDto);
        URI uriLocation = builder.path("/items/{id}")
                .buildAndExpand(viewItemDto.getId()).toUri();

        return ResponseEntity
                .created(uriLocation)
                .body(viewItemDto);
    }

    @GetMapping("/items/by/owner/{ownerId}")
    public ResponseEntity<List<ItemEntity>> getAll(@PathVariable(name = "ownerId") Long ownerId) {

        List<ItemEntity> allWithOwnerId = this.itemService.getAllWithOwnerId(ownerId);

        return ResponseEntity.ok(allWithOwnerId);
    }

}
