package com.yosep.product.jpa.cart.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.product.data.dto.SelectedProductDtoForCart;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collections;
import java.util.Map;


@Data
public abstract class CartDto extends RepresentationModel<CartDto> {
    private final String cartId;
    private final String userId;
    private Map<String, SelectedProductDtoForCart> cartProducts = Collections.emptyMap();

    public CartDto() {
        cartId = "";
        userId = "";
        cartProducts = Collections.emptyMap();
    }

    public CartDto(Cart cart) {
        this.cartId = cart.getCartId();
        this.userId = cart.getUserId();
    }

    @QueryProjection
    public CartDto(String cartId, String userId) {
        this.cartId = cartId;
        this.userId = userId;
    }
}
