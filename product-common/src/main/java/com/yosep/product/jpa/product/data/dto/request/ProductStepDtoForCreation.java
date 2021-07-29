package com.yosep.product.jpa.product.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ProductStepDtoForCreation {
    @Min(0)
    private final long totalPrice;

    @NotEmpty
    private final List<@NotNull OrderProductDtoForCreation> orderProductDtos;

    @NotEmpty
    private String state = "READY";
}
