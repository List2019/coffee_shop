package com.epam.swissre.model.beverage;

import java.math.BigDecimal;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.ProductName;

public class Beverage extends AbstractProduct {

    public Beverage(ProductName name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public BigDecimal getPrice() {
        return super.price;
    }

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}
