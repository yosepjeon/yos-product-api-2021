package com.yosep.product.jpa.category.data.dto.response;

import com.yosep.product.jpa.category.data.entity.Category;
import lombok.*;

import java.util.Collections;
import java.util.List;

public class SelectedCategoryDto extends CategoryDto {
    private final List<Category> childs;

    public SelectedCategoryDto() {
        super();
        this.childs = Collections.emptyList();
    }

    @Builder
    public SelectedCategoryDto(long id, String name, List<Category> childs) {
        super(id, name);

        if (childs.isEmpty()) {
            this.childs = Collections.emptyList();
        } else {
            this.childs = childs;
        }
    }

    public SelectedCategoryDto(Category category) {
        super(category);
        this.childs = category.getChilds().isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(category.getChilds());
    }

}
