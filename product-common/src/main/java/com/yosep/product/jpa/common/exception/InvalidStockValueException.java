package com.yosep.product.jpa.common.exception;

public class InvalidStockValueException extends RuntimeException {
    public InvalidStockValueException(String message) {
        super(message);
    }
}