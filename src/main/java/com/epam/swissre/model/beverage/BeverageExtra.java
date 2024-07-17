package com.epam.swissre.model.beverage;

import java.math.BigDecimal;

public class BeverageExtra {
    private final BeverageExtraName name;
    private BigDecimal price;

    public BeverageExtra(BeverageExtraName name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BeverageExtraName getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}
