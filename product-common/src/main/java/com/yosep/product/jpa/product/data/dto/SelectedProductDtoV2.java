package com.yosep.product.jpa.product.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.yosep.product.jpa.product.data.entity.ProductDiscount;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class SelectedProductDtoV2 extends RepresentationModel<SelectedProductDtoV2> {
    private final String productId;

    private final String productName;

    private final long productPrice;

    private final long stockQuantity;

    private final ProductDiscount productDiscount;

    @Setter
    private SelectedProductProfileImageDto thumbnail;

    @QueryProjection
    public SelectedProductDtoV2(String productId, String productName, long productPrice, long stockQuantity, ProductDiscount productDiscount, String thumnailId, String thumbnailUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.productDiscount = productDiscount;
        this.thumbnail = new SelectedProductProfileImageDto(thumbnailUrl);
    }
}
