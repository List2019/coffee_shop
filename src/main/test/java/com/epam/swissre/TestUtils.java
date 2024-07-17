package com.epam.swissre;

import static com.epam.swissre.utils.PricesHolder.BACON_ROLL_PRICE;
import static com.epam.swissre.utils.PricesHolder.EXTRA_MILK_PRICE;
import static com.epam.swissre.utils.PricesHolder.FOAMED_MILK_PRICE;
import static com.epam.swissre.utils.PricesHolder.SMALL_COFFEE_PRICE;
import static com.epam.swissre.utils.PricesHolder.SPECIAL_ROAST_COFFEE_PRICE;

import java.util.ArrayList;
import java.util.List;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.beverage.Beverage;
import com.epam.swissre.model.beverage.BeverageExtra;
import com.epam.swissre.model.beverage.BeverageExtraName;
import com.epam.swissre.model.beverage.BeverageSize;
import com.epam.swissre.model.beverage.SizedBeverage;
import com.epam.swissre.model.snack.Snack;

public class TestUtils {
    public static List<AbstractProduct> getProducts() {
        List<BeverageExtra> extrasWithSmallPrice = List.of(
                new BeverageExtra(BeverageExtraName.EXTRA_MILK, EXTRA_MILK_PRICE),
                new BeverageExtra(BeverageExtraName.SPECIAL_ROAST_COFFEE, SPECIAL_ROAST_COFFEE_PRICE)
        );
        List<BeverageExtra> extrasWithBigPrice = List.of(
                new BeverageExtra(BeverageExtraName.EXTRA_MILK, EXTRA_MILK_PRICE),
                new BeverageExtra(BeverageExtraName.SPECIAL_ROAST_COFFEE, SPECIAL_ROAST_COFFEE_PRICE),
                new BeverageExtra(BeverageExtraName.FOAMED_MILK, FOAMED_MILK_PRICE)
        );

        List<Beverage> beverages = List.of(
                new SizedBeverage(ProductName.COFFEE, SMALL_COFFEE_PRICE, BeverageSize.SMALL, extrasWithSmallPrice),
                new SizedBeverage(ProductName.COFFEE, SMALL_COFFEE_PRICE, BeverageSize.SMALL, extrasWithBigPrice)
        );

        List<Snack> snacks = List.of(new Snack(ProductName.BACON_ROLL, BACON_ROLL_PRICE));

        List<AbstractProduct> products = new ArrayList<>();
        products.addAll(beverages);
        products.addAll(snacks);
        return products;
    }

    private TestUtils() {}
}
