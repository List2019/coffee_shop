package com.epam.swissre.service;

import static com.epam.swissre.utils.Constants.INVALID_BEVERAGE_SIZE_ERROR_MESSAGE;
import static com.epam.swissre.utils.PricesHolder.FRESHLY_SQUEEZED_ORANGE_JUICE_PRICE;
import static com.epam.swissre.utils.PricesHolder.LARGE_COFFEE_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.epam.swissre.exception.InvalidBeverageSizeException;
import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.beverage.Beverage;
import com.epam.swissre.model.beverage.BeverageExtraName;
import com.epam.swissre.model.beverage.BeverageSize;
import com.epam.swissre.model.beverage.SizedBeverage;
import com.epam.swissre.service.product.BeverageService;

class BeverageServiceTest {

    private final BeverageService beverageService = new BeverageService();

    @Test
    void shouldSuccessfullyGetBeverages() {
        List<String> orderList = List.of(
                "large coffee with extra milk",
                "small coffee with special roast",
                "bacon roll",
                "orange juice");

        List<Beverage> beverages = beverageService.getBeverages(orderList);

        assertEquals(3, beverages.size());

        Beverage firstBeverage = beverages.getFirst();
        assertEquals(ProductName.COFFEE, firstBeverage.getName());
        assertInstanceOf(SizedBeverage.class, firstBeverage);

        SizedBeverage sizedBeverage = (SizedBeverage) firstBeverage;
        assertEquals(BeverageSize.LARGE, sizedBeverage.getSize());
        assertEquals(BeverageExtraName.EXTRA_MILK, sizedBeverage.getExtras().getFirst().getName());
        assertEquals(LARGE_COFFEE_PRICE, sizedBeverage.getPriceWithoutExtras());


        Beverage lastBeverage = beverages.getLast();
        assertFalse(lastBeverage instanceof SizedBeverage);
        assertEquals(ProductName.ORANGE_JUICE, lastBeverage.getName());
        assertEquals(FRESHLY_SQUEEZED_ORANGE_JUICE_PRICE, lastBeverage.getPrice());
    }

    @Test
    void shouldSuccessfullyGetBeverageWithTwoExtras() {
        List<String> orderList = List.of("large coffee with extra milk and special roast");

        List<Beverage> beverages = beverageService.getBeverages(orderList);

        assertEquals(1, beverages.size());

        Beverage firstBeverage = beverages.getFirst();
        assertEquals(ProductName.COFFEE, firstBeverage.getName());
        assertInstanceOf(SizedBeverage.class, firstBeverage);

        SizedBeverage sizedBeverage = (SizedBeverage) firstBeverage;
        assertEquals(BeverageSize.LARGE, sizedBeverage.getSize());

        assertEquals(2, sizedBeverage.getExtras().size());
        assertEquals(BeverageExtraName.EXTRA_MILK, sizedBeverage.getExtras().getFirst().getName());
        assertEquals(BeverageExtraName.SPECIAL_ROAST_COFFEE, sizedBeverage.getExtras().getLast().getName());
    }

    @Test
    void shouldSuccessfullyGetBeverageWithoutExtras() {
        List<String> orderList = List.of("medium coffee");

        List<Beverage> beverages = beverageService.getBeverages(orderList);

        assertEquals(1, beverages.size());

        Beverage firstBeverage = beverages.getFirst();
        assertEquals(ProductName.COFFEE, firstBeverage.getName());
        assertInstanceOf(SizedBeverage.class, firstBeverage);

        SizedBeverage sizedBeverage = (SizedBeverage) firstBeverage;
        assertEquals(BeverageSize.MEDIUM, sizedBeverage.getSize());

        assertTrue(sizedBeverage.getExtras().isEmpty());
    }

    @Test
    void throwsExceptionWhenInvalidBeverageSize() {
        List<String> orderList = List.of("huge coffee with extra milk and special roast");

        InvalidBeverageSizeException exception = assertThrows(InvalidBeverageSizeException.class, () -> beverageService.getBeverages(orderList));
        assertEquals(String.format(INVALID_BEVERAGE_SIZE_ERROR_MESSAGE, orderList.getFirst()), exception.getMessage());
    }
}
