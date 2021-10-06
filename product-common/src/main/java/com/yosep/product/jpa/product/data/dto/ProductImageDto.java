package com.yosep.product.jpa.product.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class ProductImageDto {
    private String productId;
    private String id;
    private String url;

    public ProductImageDto(String productId, String id, String url) {
        this.productId = productId;
        this.id = id;
        this.url = url;
    }
}
