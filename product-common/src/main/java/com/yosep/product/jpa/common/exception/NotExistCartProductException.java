package com.yosep.product.jpa.common.exception;

public class NotExistCartProductException extends RuntimeException {
    public NotExistCartProductException(String message) {
        super(message);
    }
}
