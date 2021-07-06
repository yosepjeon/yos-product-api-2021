package com.yosep.product.jpa.category.data.dto.response;

import com.yosep.product.jpa.category.data.entity.Category;

public class CreatedCategoryDto extends CategoryDto {
    public CreatedCategoryDto() {
        super();
    }

    public CreatedCategoryDto(long id, String name) {
        super(id, name);
    }

    public CreatedCategoryDto(Category category) {
        super(category);
    }
}
