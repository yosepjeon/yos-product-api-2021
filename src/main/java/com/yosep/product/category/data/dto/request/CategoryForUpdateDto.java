package com.yosep.product.category.data.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryForUpdateDto {
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private long parentId;
}
