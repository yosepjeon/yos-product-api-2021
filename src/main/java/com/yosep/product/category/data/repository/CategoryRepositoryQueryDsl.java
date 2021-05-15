package com.yosep.product.category.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryQueryDsl {
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CategoryRepositoryQueryDsl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
