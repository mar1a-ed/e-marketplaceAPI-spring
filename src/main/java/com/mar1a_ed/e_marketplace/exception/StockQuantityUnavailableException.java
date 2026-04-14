package com.mar1a_ed.e_marketplace.exception;

public class StockQuantityUnavailableException extends RuntimeException {

    public StockQuantityUnavailableException(String message) {
        super(message);
    }
}
