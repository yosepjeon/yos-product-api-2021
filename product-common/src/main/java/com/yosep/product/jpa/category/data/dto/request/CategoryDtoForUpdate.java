package com.yosep.product.jpa.category.data.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDtoForUpdate {
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private long parentId;
}
