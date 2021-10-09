package com.yosep.product.jpa.product.data.dto.request;

import com.yosep.product.jpa.product.data.entity.ProductDescriptionImage;
import com.yosep.product.jpa.product.data.entity.ProductDiscount;
import com.yosep.product.jpa.product.data.entity.ProductImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
 * ModelStruct에서 @Setter없이 @Builder로 사용하려면
 * DTO, Entity 두쪽다 @Builder있어야함
 */
//@Builder
@Data
public class ProductDtoForCreation {
    private String productId = "";

    @NotBlank
    private String productName = "";

    @NotBlank
    private String companyCode = "";

    @Min(0)
    private long productPrice = 0;

    @Min(0)
    private long stockQuantity = 0;

    private String productDetail = "";

    //    @NotBlank
    private String category;

    private ProductDiscount productDiscount;

    private List<ProductImageDtoForCreation> productImages = Collections.emptyList();

    private List<ProductDescriptionImageDtoForCreation> productDescriptionImages = Collections.emptyList();

    private List<ProductProfileImageDtoForCreation> productProfileImageURLs = Collections.emptyList();

    public ProductDtoForCreation(String productName, int productPrice, int stockQuantity, String productDetail, String category, List<ProductImageDtoForCreation> productImages) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
        this.category = category;
        this.productImages = productImages;
    }
}
