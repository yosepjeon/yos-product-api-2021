package com.yosep.product.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.data.entity.ProductTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import static com.yosep.product.data.entity.QProductTest.productTest;

@Repository
public class ProductTestRepositoryQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public ProductTestRepositoryQueryDsl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<ProductTest> findById(String name) {
        return Optional.of(jpaQueryFactory
                .selectFrom(productTest)
                .where(productTest.id.eq(name))
                .fetchOne());
    }

}
