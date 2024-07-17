package com.epam.swissre.service;

import static com.epam.swissre.utils.Constants.FREE_BEVERAGE_THRESHOLD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.epam.swissre.model.AbstractProduct;
import com.epam.swissre.model.beverage.Beverage;
import com.epam.swissre.model.beverage.BeverageExtra;
import com.epam.swissre.model.beverage.SizedBeverage;
import com.epam.swissre.model.snack.Snack;

public class BonusProgramService {

    public List<AbstractProduct> applyBonusPrograms(List<AbstractProduct> products) {
        List<Beverage> beverages = products.stream()
                .filter(Beverage.class::isInstance)
                .map(Beverage.class::cast)
                .toList();
        List<Snack> snacks = products.stream()
                .filter(Snack.class::isInstance)
                .map(Snack.class::cast)
                .toList();

        applyEachFifthBeverageFreeBonusProgram(beverages);
        applyOneExtraIsFreeBonusProgram(beverages, snacks);

        List<AbstractProduct> result = new ArrayList<>();
        result.addAll(beverages);
        result.addAll(snacks);

        return result;
    }

    private void applyEachFifthBeverageFreeBonusProgram(List<Beverage> beverages) {
        int amountOfPurchasedBeverages = getAmountOfPreviouslyPurchasedBeverages() + beverages.size();

        if (amountOfPurchasedBeverages >= FREE_BEVERAGE_THRESHOLD) {
            int freeBeverageIndex = amountOfPurchasedBeverages - FREE_BEVERAGE_THRESHOLD;
            beverages.reversed().get(freeBeverageIndex).setPrice(BigDecimal.ZERO);
        }
    }

    private void applyOneExtraIsFreeBonusProgram(List<Beverage> beverages, List<Snack> snacks) {
        if (!beverages.isEmpty() && !snacks.isEmpty()) {

            beverages.stream()
                    .filter(SizedBeverage.class::isInstance)
                    .map(SizedBeverage.class::cast)
                    .map(SizedBeverage::getExtras)
                    .filter(extras -> !extras.isEmpty())
                    .flatMap(Collection::stream)
                    .min(Comparator.comparing(BeverageExtra::getPrice))
                    .ifPresent(beverageExtraWithLowestPrice -> beverageExtraWithLowestPrice.setPrice(BigDecimal.ZERO));
        }
    }

    /**
     * <p>Since we don't have a real customer stamp card program, this method uses hardcoded number as number of previously purchased beverages.
     */
    public int getAmountOfPreviouslyPurchasedBeverages() {
        return 2;
    }
}
