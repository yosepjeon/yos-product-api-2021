package com.yosep.product.jpa.product.data.dto;

import com.yosep.product.jpa.product.data.entity.Product;
import lombok.Data;

@Data
public class SelectedProductDtoForCart {
    private String productId;
    private String productName;
    private long productPrice;
}
