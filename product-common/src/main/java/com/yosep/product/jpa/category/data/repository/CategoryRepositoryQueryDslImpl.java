package com.yosep.product.jpa.category.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.jpa.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.jpa.category.data.entity.Category;
import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.*;

import static com.yosep.product.jpa.category.data.entity.QCategory.*;

import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class CategoryRepositoryQueryDslImpl implements CategoryRepositoryQueryDsl{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<List<SelectedCategoryDto>> findByName(String name) {
        List<Category> categoryEntities = jpaQueryFactory.selectFrom(category)
                .where(category.name.eq(name))
                .fetch();

        List<SelectedCategoryDto> categoryDtos = categoryEntities.stream()
                .map(c -> new SelectedCategoryDto(c))
                .collect(toUnmodifiableList());

        return categoryDtos.isEmpty() ? Optional.empty() : Optional.of(categoryDtos);
    }

    @Override
    public Optional<List<SelectedCategoryDto>> findAllByParentIsNotNull(String id) {
        List<Category> categoryEntities = jpaQueryFactory.selectFrom(category)
                .where(category.parentCategory.categoryId.eq(id))
                .fetch();

        List<SelectedCategoryDto> categories = categoryEntities.stream()
                .map(c -> new SelectedCategoryDto(c))
                .collect(toUnmodifiableList());

        return categories.isEmpty() ? Optional.empty() : Optional.of(categories);
    }

    @Override
    public Optional<List<Category>> findAllByParentIsNull() {
        List<Category> categoryEntities = jpaQueryFactory.selectFrom(category)
                .where(category.parentCategory.isNull())
                .fetch();

//        List<SelectedCategoryDto> categories = categoryEntities.stream()
//                .map(c -> new SelectedCategoryDto(c))
//                .collect(toUnmodifiableList());

        return categoryEntities.isEmpty() ? Optional.empty() : Optional.of(categoryEntities);
    }
}
