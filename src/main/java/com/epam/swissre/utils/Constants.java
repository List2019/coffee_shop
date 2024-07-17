package com.epam.swissre.utils;

import static com.epam.swissre.model.beverage.BeverageExtraName.EXTRA_MILK;
import static com.epam.swissre.model.beverage.BeverageExtraName.FOAMED_MILK;
import static com.epam.swissre.model.beverage.BeverageExtraName.SPECIAL_ROAST_COFFEE;
import static com.epam.swissre.model.ProductName.BACON_ROLL;
import static com.epam.swissre.model.ProductName.COFFEE;
import static com.epam.swissre.model.ProductName.ORANGE_JUICE;
import static com.epam.swissre.utils.PricesHolder.BACON_ROLL_PRICE;
import static com.epam.swissre.utils.PricesHolder.EXTRA_MILK_PRICE;
import static com.epam.swissre.utils.PricesHolder.FOAMED_MILK_PRICE;
import static com.epam.swissre.utils.PricesHolder.FRESHLY_SQUEEZED_ORANGE_JUICE_PRICE;
import static com.epam.swissre.utils.PricesHolder.LARGE_COFFEE_PRICE;
import static com.epam.swissre.utils.PricesHolder.MEDIUM_COFFEE_PRICE;
import static com.epam.swissre.utils.PricesHolder.SMALL_COFFEE_PRICE;
import static com.epam.swissre.utils.PricesHolder.SPECIAL_ROAST_COFFEE_PRICE;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.epam.swissre.model.beverage.BeverageExtraName;
import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.beverage.BeverageSize;
import com.epam.swissre.model.beverage.SizedBeveragePriceId;

public class Constants {
    public static final String EMPTY_ORDER_LIST_ERROR_MESSAGE = "Order list is empty!";
    public static final String INVALID_BEVERAGE_SIZE_ERROR_MESSAGE = "Order [%s] contains invalid beverage size!";

    public static final String ORDER_SEPARATOR = ",";
    public static final int FREE_BEVERAGE_THRESHOLD = 5;

    public static final List<String> BEVERAGES_NAMES_IN_ORDER = List.of(
            COFFEE.getNameInOrder(),
            ORANGE_JUICE.getNameInOrder()
    );
    public static final List<String> SNACKS_NAMES_IN_ORDER = List.of(BACON_ROLL.getNameInOrder());


    public static final Map<ProductName, BigDecimal> REGULAR_PRODUCT_PRICE;
    public static final Map<SizedBeveragePriceId, BigDecimal> SIZED_BEVERAGE_PRICE;
    public static final Map<BeverageExtraName, BigDecimal> BEVERAGE_EXTRA_PRICE;

    static {
        REGULAR_PRODUCT_PRICE = Map.of(
                BACON_ROLL, BACON_ROLL_PRICE,
                ORANGE_JUICE, FRESHLY_SQUEEZED_ORANGE_JUICE_PRICE
        );

        SIZED_BEVERAGE_PRICE = Map.of(
                new SizedBeveragePriceId(COFFEE, BeverageSize.SMALL), SMALL_COFFEE_PRICE,
                new SizedBeveragePriceId(COFFEE, BeverageSize.MEDIUM), MEDIUM_COFFEE_PRICE,
                new SizedBeveragePriceId(COFFEE, BeverageSize.LARGE), LARGE_COFFEE_PRICE
        );

        BEVERAGE_EXTRA_PRICE = Map.of(
                EXTRA_MILK, EXTRA_MILK_PRICE,
                FOAMED_MILK, FOAMED_MILK_PRICE,
                SPECIAL_ROAST_COFFEE, SPECIAL_ROAST_COFFEE_PRICE
        );
    }

    private Constants() {}
}
