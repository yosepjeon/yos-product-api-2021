package com.yosep.product.jpa.cart.data.dto.response;

import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.cart.data.vo.CartProductVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collections;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public abstract class CartDto extends RepresentationModel<CartDto> {
    private final String cartId;
    private final String userId;
    private final Map<String, CartProductVo> cartProducts;

    public CartDto() {
        this.cartId = "";
        this.userId = "";
        this.cartProducts = Collections.emptyMap();
    }

    public CartDto(Cart cart) {
        this.cartId = cart.getCartId();
        this.userId = cart.getUserId();
        this.cartProducts = cart.getCartProducts();
    }
}
