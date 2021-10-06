package com.yosep.product.jpa.product.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class ProductDescriptionImageDto {
    private String id;
    private String url;
}
