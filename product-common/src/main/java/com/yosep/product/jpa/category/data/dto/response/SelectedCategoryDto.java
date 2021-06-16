package com.yosep.product.jpa.category.data.dto.response;

import com.yosep.product.jpa.category.data.entity.Category;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collections;
import java.util.List;

@Data
public class SelectedCategoryDto extends RepresentationModel<SelectedCategoryDto> {
    private final long id;
    private final String name;
    private final List<Category> childs;

    public SelectedCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.childs = Collections.unmodifiableList(category.getChilds());
    }
}
