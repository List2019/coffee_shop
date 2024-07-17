package com.epam.swissre.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.service.product.BeverageService;
import com.epam.swissre.service.product.ProductService;
import com.epam.swissre.service.product.SnackService;

class ProductServiceTest {

    private final ProductService productService = new ProductService(new BeverageService(), new SnackService());

    @Test
    void shouldSuccessfullyReturnProducts() {
        List<String> orderList = List.of("large coffee with extra milk and special roast", "small coffee with special roast", "bacon roll", "orange juice");

        List<AbstractProduct> products = productService.getProducts(orderList);

        assertEquals(4, products.size());
    }
}
