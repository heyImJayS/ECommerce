package dev.jays.ecommerce.models;

public enum StockAvailability {
    INSTOCK("InStock"),
    OUTSTOCK("OutStock");
    private final String text;
    StockAvailability(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
