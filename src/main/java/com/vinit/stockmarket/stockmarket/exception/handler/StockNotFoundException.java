package com.vinit.stockmarket.stockmarket.exception.handler;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String message) {
        super(message);
    }
}
