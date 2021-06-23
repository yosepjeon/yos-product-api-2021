package com.yosep.product.jpa.product.data.dto.response;

import com.yosep.product.jpa.product.data.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Getter
public class CreatedProductDto extends RepresentationModel<CreatedProductDto> {
    @NotNull
    private final String productId;

    @NotNull
    private final String productName;

    @NotNull
    private final long productPrice;

    @NotNull
    private final long stockQuantity;

    private final String productDetail;

    public CreatedProductDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.stockQuantity = product.getStockQuantity();
        this.productDetail = product.getProductDetail();
    }
}
