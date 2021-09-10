package com.yosep.product.cart.data.dto.response;

import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.cart.data.vo.CartProductVo;
import com.yosep.product.jpa.category.data.dto.response.SelectedCategoryDto;
import org.springframework.hateoas.RepresentationModel;

import java.util.Map;

public class SelectedCartDto extends RepresentationModel<SelectedCartDto> {
    private String cartId;

    private String userId;

    private Map<String, CartProductVo> cartProducts;

    public SelectedCartDto(Cart cart) {
        this.cartId = cart.getCartId();
        this.userId = cart.getUserId();
        this.cartProducts = cart.getCartProducts();
    }
}
