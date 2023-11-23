package org.example;

public enum Task {
    SHOW_PHONES, SORT_BY_PRICE, FILTER_BY_PRICE, AMOUNT_OF_MODELS, GET_BRANDS, GET_SPECIFICATIONS_OF_MODELS;

    public static Task Option(String choice) {
        return switch (choice) {
            case "1" -> SHOW_PHONES;
            case "2" -> SORT_BY_PRICE;
            case "3" -> FILTER_BY_PRICE;
            case "4" -> AMOUNT_OF_MODELS;
            case "5" -> GET_BRANDS;
            case "6" -> GET_SPECIFICATIONS_OF_MODELS;
            default -> null;
        };
    }
}
