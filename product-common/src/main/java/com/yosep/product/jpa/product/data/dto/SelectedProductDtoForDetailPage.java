package com.yosep.product.jpa.product.data.dto;

import com.yosep.product.jpa.category.data.dto.response.CategoryDto;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class SelectedProductDtoForDetailPage {
    private String productId;
    private String productName;
    private String companyCode;
    private long productPrice;
    private long stockQuantity;
    private String productDetail;
    private CategoryDto categoryDto;
    private List<ProductImageDto> productImages = Collections.emptyList();
//    private List<>
}
