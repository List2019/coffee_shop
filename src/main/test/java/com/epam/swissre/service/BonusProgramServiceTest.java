package com.epam.swissre.service;

import static com.epam.swissre.TestConstants.EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN;
import static com.epam.swissre.utils.PricesHolder.BACON_ROLL_PRICE;
import static com.epam.swissre.utils.PricesHolder.EXTRA_MILK_PRICE;
import static com.epam.swissre.utils.PricesHolder.FRESHLY_SQUEEZED_ORANGE_JUICE_PRICE;
import static com.epam.swissre.utils.PricesHolder.SMALL_COFFEE_PRICE;
import static com.epam.swissre.utils.PricesHolder.SPECIAL_ROAST_COFFEE_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.epam.swissre.TestUtils;
import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.beverage.Beverage;
import com.epam.swissre.model.beverage.BeverageExtra;
import com.epam.swissre.model.beverage.BeverageExtraName;
import com.epam.swissre.model.beverage.BeverageSize;
import com.epam.swissre.model.beverage.SizedBeverage;
import com.epam.swissre.model.snack.Snack;

class BonusProgramServiceTest {

    private final BonusProgramService bonusProgramService = new BonusProgramService();

    @Test
    void shouldSuccessfullyApplyEachFifthBeverageFreeBonusProgram() {
        List<Beverage> beverages = List.of(
                new Beverage(ProductName.ORANGE_JUICE, FRESHLY_SQUEEZED_ORANGE_JUICE_PRICE),
                new Beverage(ProductName.BACON_ROLL, BACON_ROLL_PRICE),
                new SizedBeverage(ProductName.COFFEE, SMALL_COFFEE_PRICE, BeverageSize.SMALL, List.of())
        );

        List<AbstractProduct> products = new ArrayList<>(beverages);
        List<AbstractProduct> productsWithAppliedBonusPrograms = bonusProgramService.applyBonusPrograms(products);

        assertEquals(BigDecimal.ZERO, productsWithAppliedBonusPrograms.getLast().getPrice());
    }

    @Test
    void shouldSuccessfullyApplyOneExtraIsFreeBonusProgram() {
        List<AbstractProduct> products = TestUtils.getProducts();

        List<AbstractProduct> productsWithAppliedBonusPrograms = bonusProgramService.applyBonusPrograms(products);

        SizedBeverage sizedBeverage = productsWithAppliedBonusPrograms.stream()
                .filter(product -> product instanceof SizedBeverage)
                .map(SizedBeverage.class::cast)
                .findFirst()
                .orElseThrow();

        BeverageExtra beverageExtra = sizedBeverage.getExtras().stream()
                .filter(extra -> BeverageExtraName.EXTRA_MILK.equals(extra.getName()))
                .findFirst()
                .orElseThrow();

        assertEquals(BigDecimal.ZERO, beverageExtra.getPrice());
    }

    @Test
    void shouldNotApplyBonusProgramWithoutSnacks() {
        List<BeverageExtra> extras = List.of(
                new BeverageExtra(BeverageExtraName.EXTRA_MILK, EXTRA_MILK_PRICE),
                new BeverageExtra(BeverageExtraName.SPECIAL_ROAST_COFFEE, SPECIAL_ROAST_COFFEE_PRICE)
        );
        List<Beverage> beverages = List.of(
                new SizedBeverage(ProductName.COFFEE, SMALL_COFFEE_PRICE, BeverageSize.SMALL, extras)
        );

        List<AbstractProduct> products = new ArrayList<>(beverages);
        List<AbstractProduct> productsWithAppliedBonusPrograms = bonusProgramService.applyBonusPrograms(products);

        SizedBeverage sizedBeverage = productsWithAppliedBonusPrograms.stream()
                .filter(product -> product instanceof SizedBeverage)
                .map(SizedBeverage.class::cast)
                .findFirst()
                .orElseThrow();

        assertEquals(EXTRA_MILK_PRICE, sizedBeverage.getExtras().getFirst().getPrice());
        assertEquals(SPECIAL_ROAST_COFFEE_PRICE, sizedBeverage.getExtras().getLast().getPrice());
    }

    @Test
    void shouldNotApplyBonusProgramWithoutBeverages() {
        List<Snack> snacks = List.of(new Snack(ProductName.BACON_ROLL, BACON_ROLL_PRICE));
        List<AbstractProduct> products = new ArrayList<>(snacks);

        try {
            List<AbstractProduct> productsWithAppliedBonusPrograms = bonusProgramService.applyBonusPrograms(products);
            assertEquals(1, productsWithAppliedBonusPrograms.size());
        } catch (Exception e) {
            fail(EXCEPTION_SHOULD_NOT_HAVE_BEEN_THROWN);
        }
    }
}
