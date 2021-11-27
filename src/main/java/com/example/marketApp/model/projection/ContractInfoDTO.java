package com.example.marketApp.model.projection;

import java.math.BigDecimal;

public class ContractInfoDTO {

    private Long id;
    private String seller;
    private String buyer;
    private String item;
    private BigDecimal price;
    private boolean active;

    public ContractInfoDTO(Long id, String seller, String buyer, String item, BigDecimal price, boolean active) {
        this.id = id;
        this.seller = seller;
        this.buyer = buyer;
        this.item = item;
        this.price = price;
        this.active = active;
    }

    public ContractInfoDTO(){}

    public Long getId() {
        return id;
    }

    public ContractInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public ContractInfoDTO setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public String getBuyer() {
        return buyer;
    }

    public ContractInfoDTO setBuyer(String buyer) {
        this.buyer = buyer;
        return this;
    }

    public String getItem() {
        return item;
    }

    public ContractInfoDTO setItem(String item) {
        this.item = item;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ContractInfoDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ContractInfoDTO setActive(boolean active) {
        this.active = active;
        return this;
    }
}
