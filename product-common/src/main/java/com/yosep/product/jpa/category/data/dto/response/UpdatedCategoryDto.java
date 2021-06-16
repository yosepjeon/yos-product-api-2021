package com.yosep.product.jpa.category.data.dto.response;

import com.yosep.product.jpa.category.data.entity.Category;
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
