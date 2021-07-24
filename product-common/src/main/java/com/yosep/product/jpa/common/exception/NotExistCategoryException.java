package com.yosep.product.jpa.common.exception;

public class NotExistCategoryException extends RuntimeException {
    public NotExistCategoryException(String message) {
        super(message);
    }
}
