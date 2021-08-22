package com.yosep.product.jpa.cart.data.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class CartCreationDto {
    private String cartId;
    private String userId;
}
