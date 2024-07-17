package com.epam.swissre.model.snack;

import java.math.BigDecimal;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.ProductName;

public class Snack extends AbstractProduct {
    public Snack(ProductName name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public BigDecimal getPrice() {
        return super.price;
    }
}
