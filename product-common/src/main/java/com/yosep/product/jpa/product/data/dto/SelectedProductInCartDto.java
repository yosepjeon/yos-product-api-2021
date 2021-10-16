package com.yosep.product.jpa.product.data.dto;

import com.yosep.product.jpa.product.data.entity.ProductImage;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class SelectedProductInCartDto {
    private final String productName;
    private final long productPrice;
    private final String productImage;
}
