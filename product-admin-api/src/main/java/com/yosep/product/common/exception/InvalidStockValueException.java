package com.yosep.product.common.exception;

public class InvalidStockValueException extends RuntimeException {
    public InvalidStockValueException(String message) {
        super(message);
    }
}