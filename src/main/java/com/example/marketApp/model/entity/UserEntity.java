package com.example.marketApp.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(nullable = false)
    private BigDecimal account;

    public UserEntity(){}

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public UserEntity setAccount(BigDecimal account) {
        this.account = account;
        return this;
    }

    @Override
    public UserEntity setId(Long id) {
        super.setId(id);
        return this;
    }
}
