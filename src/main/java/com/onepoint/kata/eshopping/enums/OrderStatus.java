package com.onepoint.kata.eshopping.enums;

public enum OrderStatus {
    PENDING("pending"),
    CANCELLED("cancelled"),
    PAID("paid");
    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}