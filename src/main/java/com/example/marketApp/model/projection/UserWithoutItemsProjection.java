package com.example.marketApp.model.projection;

import java.math.BigDecimal;

public interface UserWithoutItemsProjection {

    Long getId();

    String getUsername();

    BigDecimal getAccount();
}
