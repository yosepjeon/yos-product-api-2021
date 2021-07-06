package com.yosep.product.jpa.cart.data.dto.request;

import lombok.Getter;

@Getter
public class CartProductDto {
    private String productId;

    private Long amount;
}
