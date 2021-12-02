package com.example.marketApp.model.dto;

import java.math.BigDecimal;

public class UpdateContractDTO {

   private Long itemId;
   private BigDecimal price;

   public UpdateContractDTO(){}

    public Long getItemId() {
        return itemId;
    }

    public UpdateContractDTO setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UpdateContractDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
