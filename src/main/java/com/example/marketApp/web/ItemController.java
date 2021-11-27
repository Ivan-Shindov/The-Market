package com.example.marketApp.web;

import com.example.marketApp.model.dto.PostItemDto;
import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.projection.AllItemsProjectionDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
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
    public ResponseEntity<ItemProjectionDTO> getItemById(@PathVariable Long id) {
        ItemProjectionDTO itemView = itemService.getItemById(id);

        return ResponseEntity.ok(itemView);
    }

    @PostMapping("/items/create")
    public ResponseEntity<ViewItemDto> create(@RequestBody PostItemDto postItemDto,
                                              UriComponentsBuilder builder) {

       try {
           ViewItemDto viewItemDto = itemService.createItem(postItemDto);
           URI uriLocation = builder.path("/items/{id}")
                   .buildAndExpand(viewItemDto.getId()).toUri();

           return ResponseEntity
                   .created(uriLocation)
                   .body(viewItemDto);

       } catch (IllegalArgumentException ex) {
           return ResponseEntity
                   .notFound()
                   .build();
       }

    }

    @GetMapping("/items/by/owner/{ownerId}")
    public ResponseEntity<List<AllItemsProjectionDTO>> getAllItems(@PathVariable(name = "ownerId") Long ownerId) {

        List<AllItemsProjectionDTO> allWithOwnerId = this.itemService.getAllWithOwnerId(ownerId);

        return ResponseEntity.ok(allWithOwnerId);
    }

}
