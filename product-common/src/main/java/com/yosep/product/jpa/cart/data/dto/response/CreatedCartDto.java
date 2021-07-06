package com.yosep.product.jpa.cart.data.dto.response;

import com.yosep.product.jpa.cart.data.entity.Cart;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

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
