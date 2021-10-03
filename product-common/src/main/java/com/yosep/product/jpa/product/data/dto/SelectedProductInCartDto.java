package com.yosep.product.jpa.product.data.dto;

import com.yosep.product.jpa.product.data.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class SelectedProductInCartDto {
    private final String productName;
    private final long productPrice;
    private final String productImage;
}
