package com.example.marketApp.model.dto;

import java.math.BigDecimal;

public class ViewContractDto {

    private Long id;
    private Long sellerId;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public Long getSellerId() {
        return sellerId;
    }

    public ViewContractDto setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public ViewContractDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ViewContractDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ViewContractDto setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ViewContractDto setId(Long id) {
        this.id = id;
        return this;
    }
}
