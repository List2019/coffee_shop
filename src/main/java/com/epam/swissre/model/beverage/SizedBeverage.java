package com.epam.swissre.model.beverage;

import java.math.BigDecimal;
import java.util.List;

import com.epam.swissre.model.ProductName;

public class SizedBeverage extends Beverage {
    private final List<BeverageExtra> extras;
    private final BeverageSize size;

    public SizedBeverage(ProductName name, BigDecimal price, BeverageSize size, List<BeverageExtra> extras) {
        super(name, price);
        this.size = size;
        this.extras = extras;
    }

    public BeverageSize getSize() {
        return size;
    }

    public List<BeverageExtra> getExtras() {
        return extras;
    }

    public BigDecimal getPriceWithoutExtras() {
        return super.getPrice();
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal totalBeverageExtrasPrice = extras.stream()
                .map(BeverageExtra::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return super.getPrice().add(totalBeverageExtrasPrice);
    }
}
