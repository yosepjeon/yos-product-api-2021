package com.yosep.product.jpa.product.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Data
public class SelectedProductDtoForDetailPage {
    private final String productId;
    private final String productName;
    private final String companyCode;
    private final long productPrice;
    private final long stockQuantity;
    private final String productDetail;
    /*
     * TODO: 리팩토링
     * 컬렉션 -> 일급 컬렉션
     */
    private final List<SelectedProductImageDto> productImages;
    private final List<SelectedProductCommentDto> comments;
    private final List<SelectedProductDescriptionImageDto> descriptions;

    public SelectedProductDtoForDetailPage(String productId, String productName, String companyCode, long productPrice, long stockQuantity, String productDetail, List<SelectedProductImageDto> productImages, List<SelectedProductCommentDto> comments, List<SelectedProductDescriptionImageDto> descriptions) {
        this.productId = productId;
        this.productName = productName;
        this.companyCode = companyCode;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
        this.productImages = Collections.unmodifiableList(productImages);
        this.comments = Collections.unmodifiableList(comments);
        this.descriptions = Collections.unmodifiableList(descriptions);
    }
}
