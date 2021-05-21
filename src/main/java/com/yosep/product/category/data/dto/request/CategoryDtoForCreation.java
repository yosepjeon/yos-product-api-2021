package com.yosep.product.category.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class CategoryDtoForCreation {
    @NotNull
    private String name;

    @NotNull
    private Long parentId;
}
