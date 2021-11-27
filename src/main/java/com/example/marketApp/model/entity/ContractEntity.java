package com.example.marketApp.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class ContractEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemEntity item;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private boolean active;

    public ContractEntity(){}

    public UserEntity getSeller() {
        return seller;
    }

    public ContractEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public ContractEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public ItemEntity getItem() {
        return item;
    }

    public ContractEntity setItem(ItemEntity item) {
        this.item = item;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ContractEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ContractEntity setActive(boolean active) {
        this.active = active;
        return this;
    }
}
