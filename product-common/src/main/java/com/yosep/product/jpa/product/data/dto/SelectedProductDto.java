package com.yosep.product.jpa.product.data.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.common.exception.InvalidStockValueException;
import com.yosep.product.jpa.product.data.entity.ProductDescriptionImage;
import com.yosep.product.jpa.product.data.entity.ProductImage;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class SelectedProductDto extends RepresentationModel<SelectedProductDto> {
    private final String productId;

    private final String productName;

    private final long productPrice;

    private final long stockQuantity;

    private final String productDetail;

    private Category category;

    private List<ProductImage> productImages = Collections.emptyList();

    private List<ProductDescriptionImage> productDescriptionImages = Collections.emptyList();

    private List<ProductImage> productProfileImageURLs = Collections.emptyList();
}
