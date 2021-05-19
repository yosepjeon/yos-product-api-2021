package com.yosep.product.category.data.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.category.data.entity.Category;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class SelectedCategoryDto {
    private final long id;
    private final String name;
    private final List<Category> childs;

    public SelectedCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.childs = Collections.unmodifiableList(category.getChilds());
    }
}
