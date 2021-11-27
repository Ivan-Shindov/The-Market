package com.example.marketApp.model.projection;

import java.math.BigDecimal;

public interface ContractInfoProjectionDTO {

    Long getId();
    String getSeller();
    String getBuyer();
    String getItem();
    BigDecimal getPrice();
    boolean getActive();

}
