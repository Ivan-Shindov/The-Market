package com.example.marketApp.model.projection;

import java.math.BigDecimal;

public class ContractInfoProjection {

    private Long id;
    private String seller;
    private String buyer;
    private String item;
    private BigDecimal price;
    private boolean active;

    public ContractInfoProjection(Long id, String seller, String buyer, String item, BigDecimal price, boolean active) {
        this.id = id;
        this.seller = seller;
        this.buyer = buyer;
        this.item = item;
        this.price = price;
        this.active = active;
    }

    public ContractInfoProjection(){}

    public Long getId() {
        return id;
    }

    public ContractInfoProjection setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public ContractInfoProjection setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public String getBuyer() {
        return buyer;
    }

    public ContractInfoProjection setBuyer(String buyer) {
        this.buyer = buyer;
        return this;
    }

    public String getItem() {
        return item;
    }

    public ContractInfoProjection setItem(String item) {
        this.item = item;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ContractInfoProjection setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ContractInfoProjection setActive(boolean active) {
        this.active = active;
        return this;
    }
}
