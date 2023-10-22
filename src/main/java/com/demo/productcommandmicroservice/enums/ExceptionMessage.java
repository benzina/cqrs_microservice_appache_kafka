package com.demo.productcommandmicroservice.enums;

public enum ExceptionMessage {
    ProductNoutFound("Product id not found:");
    private final String value;

    ExceptionMessage(String value) {
        this.value = value;
    }

    public String getValeur() {
        return this.value;
    }


}
