package com.epam.swissre.model.beverage;

import com.epam.swissre.model.ProductName;

public record SizedBeveragePriceId(ProductName productName, BeverageSize beverageSize) {
}
