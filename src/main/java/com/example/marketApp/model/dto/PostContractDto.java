package com.example.marketApp.model.dto;

import java.math.BigDecimal;

public class PostContractDto {

    private Long itemId;
    private BigDecimal price;

    public PostContractDto(){}

    public Long getItemId() {
        return itemId;
    }

    public PostContractDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PostContractDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
