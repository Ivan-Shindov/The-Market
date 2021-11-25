package com.example.marketApp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class ContractEntity extends BaseEntity {

    @ManyToOne
    private UserEntity seller;

    @ManyToOne
    private UserEntity buyer;

    @ManyToOne
    private ItemEntity item;

    @Column(precision = 0, nullable = false)
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
