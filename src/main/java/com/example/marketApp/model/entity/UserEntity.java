package com.example.marketApp.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(nullable = false)
    private BigDecimal account;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items = new ArrayList<>();

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

    public void addItem(ItemEntity item) {
        this.items.add(item);
    }

    public boolean removeItem(ItemEntity item) {
        for (ItemEntity currentItem : this.items) {
            if (currentItem.getId().longValue() == item.getId().longValue()) {
                this.items.remove(currentItem);
                return true;
            }
        }
        return false;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public UserEntity setItems(List<ItemEntity> items) {
        this.items = items;
        return this;
    }

    @Override
    public UserEntity setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public Long getId(){
        return super.getId();
    }
}
