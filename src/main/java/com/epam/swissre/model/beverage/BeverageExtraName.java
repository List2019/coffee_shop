package com.epam.swissre.model.beverage;

public enum BeverageExtraName {
    EXTRA_MILK("Extra milk", "extra milk"),
    FOAMED_MILK("Foamed milk", "foamed milk"),
    SPECIAL_ROAST_COFFEE("Special roast coffee", "special roast");

    private final String displayName;
    private final String nameInOrder;

    BeverageExtraName(String displayName, String nameInOrder) {
        this.displayName = displayName;
        this.nameInOrder = nameInOrder;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getNameInOrder() {
        return nameInOrder;
    }
}
