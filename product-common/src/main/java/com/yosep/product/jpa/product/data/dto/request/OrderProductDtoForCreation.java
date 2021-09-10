package com.yosep.product.jpa.product.data.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@ToString
public class OrderProductDtoForCreation {
    @NotNull
    private final String productId;
    @Min(0)
    private final int count;
    @Setter
    @NotNull
    private String state;
    @Min(0)
    private final long price;
}
