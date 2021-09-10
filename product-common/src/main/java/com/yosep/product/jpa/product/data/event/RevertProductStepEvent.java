package com.yosep.product.jpa.product.data.event;

import com.yosep.product.jpa.product.data.dto.request.OrderProductDtoForCreation;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class RevertProductStepEvent {
    private final String eventId;
    private final List<OrderProductDtoForCreation> orderProductDtos;
}
