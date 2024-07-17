package com.epam.swissre.model;

import java.math.BigDecimal;

public abstract class AbstractProduct {
    private final ProductName name;
    protected BigDecimal price;

    protected AbstractProduct(ProductName name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductName getName() {
        return name;
    }

    public abstract BigDecimal getPrice();
}
