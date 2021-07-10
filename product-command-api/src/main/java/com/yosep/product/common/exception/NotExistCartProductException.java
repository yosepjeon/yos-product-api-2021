package com.yosep.product.common.exception;

public class NotExistCartProductException extends RuntimeException {
    public NotExistCartProductException(String message) {
        super(message);
    }
}
