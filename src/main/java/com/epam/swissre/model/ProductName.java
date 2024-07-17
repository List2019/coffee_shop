package com.epam.swissre.model;

import java.util.List;

public enum ProductName {
    BACON_ROLL("Bacon Roll", "bacon roll", false),
    ORANGE_JUICE("Freshly squeezed orange juice", "orange juice", false),
    COFFEE("Coffee", "coffee", true);

    private final String displayName;
    private final String nameInOrder;
    private final boolean isSized;

    ProductName(String displayName, String nameInOrder, boolean isSized) {
        this.displayName = displayName;
        this.nameInOrder = nameInOrder;
        this.isSized = isSized;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getNameInOrder() {
        return nameInOrder;
    }

    public boolean isSized() {
        return isSized;
    }

    public static ProductName getBeverageName(String order) {
        List<ProductName> beverageNames = List.of(COFFEE, ORANGE_JUICE);
        return getProductName(order, beverageNames);
    }

    public static ProductName getSnackName(String order) {
        List<ProductName> snackNames = List.of(BACON_ROLL);
        return getProductName(order, snackNames);
    }

    /**
     * Since we have already filtered the order, at least one product will be present for sure and we can safely use get() on the Optional.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static ProductName getProductName(String order, List<ProductName> productNames) {
        return productNames.stream()
                .filter(productName -> order.contains(productName.getNameInOrder()))
                .findFirst()
                .get();
    }
}

