package com.yosep.product.jpa.product.data.dto.response;

import com.yosep.product.jpa.product.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@ToString
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

}
