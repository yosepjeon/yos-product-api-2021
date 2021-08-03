package com.yosep.product.jpa.product.data.dto.request;

import lombok.*;

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
    @Setter
    String state = "READY";
}
