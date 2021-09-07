package com.yosep.product.jpa.product.data.event;

import com.yosep.product.jpa.product.data.dto.request.OrderProductDtoForCreation;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RevertProductStepEvent {
    private String eventId;
    private List<OrderProductDtoForCreation> orderProductDtos;
}
