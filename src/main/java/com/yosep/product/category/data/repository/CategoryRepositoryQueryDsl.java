package com.yosep.product.category.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.category.data.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.yosep.product.category.data.entity.QCategory.category;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryQueryDsl {
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CategoryRepositoryQueryDsl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<List<Category>> findByName(String name) {
        List<Category> categories = jpaQueryFactory.selectFrom(category)
                .where(category.name.eq(name))
                .fetch();

        return categories.isEmpty() ? Optional.empty() : Optional.of(categories);
    }
}
