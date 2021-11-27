package com.example.marketApp.model.dto;

public class ViewUserItemsDTO {

    private Long id;
    private String name;

    public ViewUserItemsDTO() {
    }

    public Long getId() {
        return id;
    }

    public ViewUserItemsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewUserItemsDTO setName(String name) {
        this.name = name;
        return this;
    }
}
