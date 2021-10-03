package com.yosep.product.jpa.product.data.dto.request;

import com.yosep.product.jpa.product.data.dto.CreatedProductDto;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ProductStepDtoForCreation extends RepresentationModel<ProductStepDtoForCreation> {
    @NotEmpty
    private String orderId;

//    @Min(0)
//    private final long totalPrice;

    @NotEmpty
    private final List<@NotNull OrderProductDtoForCreation> orderProductDtos;

    @NotEmpty
    @Setter
    String state;
}
