package com.yosep.product.jpa.category.data.repository;

import com.yosep.product.jpa.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.jpa.category.data.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryQueryDsl {
    Optional<List<SelectedCategoryDto>> findByName(String name);
    Optional<List<SelectedCategoryDto>> findAllByParentIsNotNull(Long id);
    Optional<List<Category>> findAllByParentIsNull();
}
