package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.category.data.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryQueryDsl {
    public Optional<List<SelectedCategoryDto>> findByName(String name);
    public Optional<List<SelectedCategoryDto>> findAllByParentIsNotNull(Long id);
    public Optional<List<Category>> findAllByParentIsNull();
}
