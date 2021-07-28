package com.yosep.product.jpa.common.exception;

public class NotEqualProductPrice extends RuntimeException {
    public NotEqualProductPrice(String message) {
        super(message);
    }
}
