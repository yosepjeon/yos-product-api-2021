package com.yosep.product.jpa.cart.data.dto.response;

import com.yosep.product.jpa.cart.data.entity.Cart;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SelectedCartDto extends CartDto {

    public SelectedCartDto() {
        super();
    }

    public SelectedCartDto(Cart cart) {
        super(cart);
    }
}
