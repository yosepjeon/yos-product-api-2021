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
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ProductDtoForCreation {
    public String productId = "";

    @NotBlank
    public String productName = "";

    @Min(0)
    public long productPrice = 0;

    @Min(0)
    public long stockQuantity = 0;

    public String productDetail = "";

    //    @NotBlank
    public String category;

    public ProductDiscount productDiscount;

    public List<MultipartFile> productImages = Collections.emptyList();

    public List<ProductDescriptionImage> productDescriptionImages = Collections.emptyList();

    public List<ProductImage> productProfileImageURLs = Collections.emptyList();

    public ProductDtoForCreation(String productName, int productPrice, int stockQuantity, String productDetail, String category, List<MultipartFile> productImages) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
        this.category = category;
        this.productImages = productImages;
    }
}
