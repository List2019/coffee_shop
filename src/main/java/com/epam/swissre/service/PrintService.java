package com.epam.swissre.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.beverage.BeverageExtra;
import com.epam.swissre.model.beverage.BeverageExtraName;
import com.epam.swissre.model.beverage.SizedBeverage;

public class PrintService {

    private static final String COFFEE_SHOP = "Coffee shop";
    private static final String TOTAL = "Total";
    private static final int SPACING_BETWEEN_PRODUCT_NAME_AND_PRODUCT_PRICE = 10;
    private static final String CURRENCY = " CHF";
    private static final String SEPARATOR = "----------------------------------------------";
    private static final String SPACE = " ";

    public void printReceipt(List<AbstractProduct> products) {
        BigDecimal total = getTotal(products);
        Integer longestProductNameLength = getLongestProductNameLength(products);
        int spacingBetweenLongestProductNameAndPrice = longestProductNameLength + SPACING_BETWEEN_PRODUCT_NAME_AND_PRODUCT_PRICE;

        System.out.println(COFFEE_SHOP);
        System.out.println(SEPARATOR);

        for (AbstractProduct product : products) {
            if (product instanceof SizedBeverage sizedBeverage) {
                printSizedBeverage(sizedBeverage, spacingBetweenLongestProductNameAndPrice);
            } else {
                String spacing = SPACE.repeat(spacingBetweenLongestProductNameAndPrice - product.getName().getDisplayName().length());
                System.out.println(product.getName().getDisplayName() + spacing + product.getPrice() + CURRENCY);
            }
        }

        System.out.println(SEPARATOR);
        System.out.println(TOTAL + SPACE.repeat(spacingBetweenLongestProductNameAndPrice - TOTAL.length()) + total + CURRENCY);
    }

    private void printSizedBeverage(SizedBeverage sizedBeverage, int spacingBetweenLongestProductNameAndPrice) {
        String sizedBeverageReceiptName = sizedBeverage.getSize() + SPACE + sizedBeverage.getName().getDisplayName();
        String spacing = SPACE.repeat(spacingBetweenLongestProductNameAndPrice - sizedBeverageReceiptName.length());
        System.out.println(sizedBeverageReceiptName + spacing + sizedBeverage.getPriceWithoutExtras() + CURRENCY);

        sizedBeverage.getExtras()
                .forEach(beverageExtra -> {
                    String extraReceiptName = "- " + beverageExtra.getName().getDisplayName();
                    String beverageExtraSpacing = SPACE.repeat(spacingBetweenLongestProductNameAndPrice - extraReceiptName.length());
                    System.out.println(extraReceiptName + beverageExtraSpacing + beverageExtra.getPrice() + CURRENCY);
                });
    }

    /**
     * We are assuming that if input is present then it's valid and contains products, so we can safely use get() on the Optional.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static Integer getLongestProductNameLength(List<AbstractProduct> products) {
        List<String> beverageExtraName = products.stream()
                .filter(SizedBeverage.class::isInstance)
                .map(SizedBeverage.class::cast)
                .map(SizedBeverage::getExtras)
                .flatMap(Collection::stream)
                .distinct()
                .map(BeverageExtra::getName)
                .map(BeverageExtraName::getDisplayName)
                .toList();
        List<String> productNames = products.stream()
                .map(AbstractProduct::getName)
                .map(ProductName::getDisplayName)
                .toList();

        return Stream.concat(productNames.stream(), beverageExtraName.stream())
                .max(Comparator.comparing(String::length))
                .map(String::length)
                .get();
    }

    private BigDecimal getTotal(List<AbstractProduct> products) {
        return products.stream()
                .map(AbstractProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
