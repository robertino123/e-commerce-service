package com.ecommerce.api.exception;

public class ProductNotFoundException extends Exception {

    private final int httpStatus;
    private final String message;

    public ProductNotFoundException(int httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
