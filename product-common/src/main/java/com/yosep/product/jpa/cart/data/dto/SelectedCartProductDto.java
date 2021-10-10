package com.yosep.product.jpa.cart.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.yosep.product.jpa.product.data.dto.SelectedProductDtoForCart;
import lombok.Data;

@Data
public class SelectedCartProductDto {
    private SelectedProductDtoForCart product;

    private long amount;


}
