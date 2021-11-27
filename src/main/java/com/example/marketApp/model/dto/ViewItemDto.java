package com.example.marketApp.model.dto;

public class ViewItemDto {

    private Long id;
    private String name;
    private String ownerUsername;

    public ViewItemDto(){}

    public String getName() {
        return name;
    }

    public ViewItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public ViewItemDto setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ViewItemDto setId(Long id) {
        this.id = id;
        return this;
    }
}
