package com.example.marketApp.model.dto;

import java.math.BigDecimal;

public class ViewContractClosedDTO {

    private Long sellerId;
    private String sellerUsername;
    private Long buyerId;
    private String buyerUsername;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public ViewContractClosedDTO(){}

    public Long getSellerId() {
        return sellerId;
    }

    public ViewContractClosedDTO setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public ViewContractClosedDTO setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public ViewContractClosedDTO setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public ViewContractClosedDTO setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public ViewContractClosedDTO setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ViewContractClosedDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ViewContractClosedDTO setActive(boolean active) {
        this.active = active;
        return this;
    }
}
