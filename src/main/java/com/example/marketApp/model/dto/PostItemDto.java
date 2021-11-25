package com.example.marketApp.model.dto;

public class PostItemDto {

    private Long id;
    private String name;
    private Long ownerId;

    public PostItemDto() {}

    public Long getId() {
        return id;
    }

    public PostItemDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PostItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public PostItemDto setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

}
