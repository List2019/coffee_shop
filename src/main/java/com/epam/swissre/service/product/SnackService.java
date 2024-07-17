package com.epam.swissre.service.product;

import static com.epam.swissre.utils.Constants.SNACKS_NAMES_IN_ORDER;
import static com.epam.swissre.utils.Constants.REGULAR_PRODUCT_PRICE;

import java.math.BigDecimal;
import java.util.List;

import com.epam.swissre.model.ProductName;
import com.epam.swissre.model.snack.Snack;

public class SnackService {

    public List<Snack> getSnacks(List<String> orderList) {
        return orderList.stream()
                .filter(order -> SNACKS_NAMES_IN_ORDER.stream().anyMatch(order::contains))
                .map(this::toSnack)
                .toList();
    }

    private Snack toSnack(String order) {
        ProductName snackName = ProductName.getSnackName(order);
        BigDecimal price = REGULAR_PRODUCT_PRICE.get(snackName);

        return new Snack(snackName, price);
    }
}
