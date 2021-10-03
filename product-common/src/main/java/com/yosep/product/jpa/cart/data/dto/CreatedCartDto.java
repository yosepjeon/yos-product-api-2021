package com.yosep.product.jpa.cart.data.dto;

import com.yosep.product.jpa.cart.data.entity.Cart;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class CreatedCartDto extends CartDto {
    public CreatedCartDto() {
        super();
    }

    public CreatedCartDto(Cart cart) {
        super(cart);
    }
}
