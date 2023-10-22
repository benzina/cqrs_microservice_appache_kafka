package com.demo.productcommandmicroservice.enums;

public enum TypeEvent {
    createdProduct("createdProduct"),
    updatedProduct("updatedProduct");
    private final String value;

    TypeEvent(String value) {
        this.value = value;
    }

    public String getValeur() {
        return this.value;
    }
}
