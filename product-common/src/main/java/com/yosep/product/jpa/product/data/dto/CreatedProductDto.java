package com.yosep.product.jpa.product.data.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Getter
@Builder
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

//    private final Category category;

//    private final
}
