package com.example.marketApp.model.dto;

import java.math.BigDecimal;

public class PostUserDto {

    private Long id;
    private String username;
    private BigDecimal account;

    public PostUserDto() {

    }

    public Long getId() {
        return id;
    }

    public PostUserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PostUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public PostUserDto setAccount(BigDecimal account) {
        this.account = account;
        return this;
    }
}
