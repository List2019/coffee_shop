package com.epam.swissre.service.product;

import java.util.ArrayList;
import java.util.List;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.beverage.Beverage;
import com.epam.swissre.model.snack.Snack;

public class ProductService {

    private final BeverageService beverageService;
    private final SnackService snackService;

    public ProductService(BeverageService beverageService, SnackService snackService) {
        this.beverageService = beverageService;
        this.snackService = snackService;
    }

    public List<AbstractProduct> getProducts(List<String> orderList) {
        List<Beverage> beverages = beverageService.getBeverages(orderList);
        List<Snack> snacks = snackService.getSnacks(orderList);

        List<AbstractProduct> products = new ArrayList<>();
        products.addAll(beverages);
        products.addAll(snacks);

        return products;
    }

}
