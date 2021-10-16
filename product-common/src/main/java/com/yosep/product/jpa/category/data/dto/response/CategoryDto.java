package com.yosep.product.jpa.category.data.dto.response;

import com.yosep.product.jpa.category.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public abstract class CategoryDto extends RepresentationModel<CategoryDto> {
    private final String categoryId;

    private final String name;

    public CategoryDto() {
        this.categoryId = "";
        this.name = "";
    }

    public CategoryDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
    }
}
