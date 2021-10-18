package com.yosep.product.jpa.cart.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.yosep.product.jpa.cart.data.entity.Cart;
import lombok.Data;

@Data
public class SelectedCartDto extends CartDto {

    public SelectedCartDto() {
        super();
    }

    public SelectedCartDto(Cart cart) {
        super(cart);
    }

    @QueryProjection
    public SelectedCartDto(String cartId, String userId) {
        super(cartId, userId);
    }
}
