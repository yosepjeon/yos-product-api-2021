package com.yosep.product.category.data.dto.response;

import com.yosep.product.category.data.entity.Category;
import lombok.Data;

@Data
public class UpdatedCategoryDto {
    private final long id;
    private final String name;

    public UpdatedCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
