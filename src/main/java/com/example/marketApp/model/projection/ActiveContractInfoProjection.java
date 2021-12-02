package com.example.marketApp.model.projection;

import java.math.BigDecimal;

public class ActiveContractInfoProjection {

    private Long sellerId;
    private String sellerUsername;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public ActiveContractInfoProjection(Long sellerId, String sellerUsername,
                                        Long itemId, BigDecimal price, boolean active) {
        this.sellerId = sellerId;
        this.sellerUsername = sellerUsername;
        this.itemId = itemId;
        this.price = price;
        this.active = active;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public ActiveContractInfoProjection setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public ActiveContractInfoProjection setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public ActiveContractInfoProjection setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ActiveContractInfoProjection setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ActiveContractInfoProjection setActive(boolean active) {
        this.active = active;
        return this;
    }
}
