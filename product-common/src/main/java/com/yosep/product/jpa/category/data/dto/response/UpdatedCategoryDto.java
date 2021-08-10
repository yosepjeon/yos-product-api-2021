package com.yosep.product.jpa.category.data.dto.response;

import com.yosep.product.jpa.category.data.entity.Category;

public class UpdatedCategoryDto extends CategoryDto {

    public UpdatedCategoryDto() {
        super();
    }

    public UpdatedCategoryDto(Category category) {
        super(category);
    }

    public UpdatedCategoryDto(String id, String name) {
        super(id, name);
    }
}
