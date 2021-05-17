package com.yosep.product.category.data.dto.response;

import com.yosep.product.category.data.entity.Category;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ReadedCategoryDto {
    private final long id;
    private final String name;
    private final Category parent;
    private final List<Category> childs;

    public ReadedCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parent = category.getParentCategory();
        this.childs = Collections.unmodifiableList(category.getChilds());
    }
}
