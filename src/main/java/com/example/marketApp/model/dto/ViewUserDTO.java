package com.example.marketApp.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewUserDTO implements Serializable {

    private Long id;
    private String username;
    private BigDecimal account;
    private List<ViewUserItemsDTO> items = new ArrayList<>();

    public ViewUserDTO(Long id, String username, BigDecimal account, List<ViewUserItemsDTO> items) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.items = items;
    }

    public ViewUserDTO(){}

    public String getUsername() {
        return username;
    }

    public ViewUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public ViewUserDTO setAccount(BigDecimal account) {
        this.account = account;
        return this;
    }

    public List<ViewUserItemsDTO> getItems() {
        return items;
    }

    public ViewUserDTO setItems(List<ViewUserItemsDTO> items) {
        this.items = items;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ViewUserDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
