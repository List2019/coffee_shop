package com.epam.swissre.service;

import static com.epam.swissre.utils.PricesHolder.BACON_ROLL_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.snack.Snack;
import com.epam.swissre.service.product.SnackService;

class SnackServiceTest {

    private final SnackService snackService = new SnackService();

    @Test
    void shouldSuccessfullyGetSnacks() {
        List<String> orderList = List.of("bacon roll", "bacon roll" ,"orange juice");

        List<Snack> snacks = snackService.getSnacks(orderList);

        assertEquals(2, snacks.size());

        Snack firstSnack = snacks.getFirst();
        Snack secondSnack = snacks.getLast();

        assertEquals(ProductName.BACON_ROLL, firstSnack.getName());
        assertEquals(ProductName.BACON_ROLL, secondSnack.getName());

        assertEquals(BACON_ROLL_PRICE, firstSnack.getPrice());
        assertEquals(BACON_ROLL_PRICE, secondSnack.getPrice());
    }
}
