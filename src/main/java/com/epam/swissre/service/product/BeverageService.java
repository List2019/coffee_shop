package com.epam.swissre.service.product;

import static com.epam.swissre.utils.Constants.BEVERAGE_EXTRA_PRICE;
import static com.epam.swissre.utils.Constants.INVALID_BEVERAGE_SIZE_ERROR_MESSAGE;
import static com.epam.swissre.utils.Constants.BEVERAGES_NAMES_IN_ORDER;
import static com.epam.swissre.utils.Constants.REGULAR_PRODUCT_PRICE;
import static com.epam.swissre.utils.Constants.SIZED_BEVERAGE_PRICE;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.epam.swissre.exception.InvalidBeverageSizeException;
import com.epam.swissre.model.beverage.BeverageExtraName;
import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.beverage.Beverage;
import com.epam.swissre.model.beverage.BeverageExtra;
import com.epam.swissre.model.beverage.BeverageSize;
import com.epam.swissre.model.beverage.SizedBeverage;
import com.epam.swissre.model.beverage.SizedBeveragePriceId;

public class BeverageService {

    public List<Beverage> getBeverages(List<String> orderList) {
        return orderList.stream()
                .filter(order -> BEVERAGES_NAMES_IN_ORDER.stream().anyMatch(order::contains))
                .map(this::toBeverage)
                .toList();
    }

    private Beverage toBeverage(String order) {
        ProductName beverageName = ProductName.getBeverageName(order);

        if (beverageName.isSized()) {
            return toSizedBeverage(order, beverageName);
        } else {
            return new Beverage(beverageName, REGULAR_PRODUCT_PRICE.get(beverageName));
        }
    }

    private SizedBeverage toSizedBeverage(String order, ProductName beverageName) {
        BeverageSize beverageSize = getBeverageSize(order);
        BigDecimal price = SIZED_BEVERAGE_PRICE.get(new SizedBeveragePriceId(beverageName, beverageSize));
        List<BeverageExtra> beverageExtra = getBeverageExtra(order);

        return new SizedBeverage(beverageName, price, beverageSize, beverageExtra);
    }

    private BeverageSize getBeverageSize(String order) {
        return Arrays.stream(BeverageSize.values())
                .filter(size -> order.contains(size.name().toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new InvalidBeverageSizeException(INVALID_BEVERAGE_SIZE_ERROR_MESSAGE, order));
    }

    private List<BeverageExtra> getBeverageExtra(String order) {
        return Arrays.stream(BeverageExtraName.values())
                .filter(beverageExtra -> order.contains(beverageExtra.getNameInOrder()))
                .map(beverageExtraName -> new BeverageExtra(beverageExtraName, BEVERAGE_EXTRA_PRICE.get(beverageExtraName)))
                .toList();
    }
}
