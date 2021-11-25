package com.example.marketApp.model.projection;

import java.io.Serializable;
import java.math.BigDecimal;

public class ViewProjectionUserDto implements Serializable {

    private String username;
    private BigDecimal account;

    public ViewProjectionUserDto(String username, BigDecimal account) {
        this.username = username;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public ViewProjectionUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public ViewProjectionUserDto setAccount(BigDecimal account) {
        this.account = account;
        return this;
    }
}
