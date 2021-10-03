package com.yosep.product.jpa.product.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.jpa.category.data.entity.QCategory;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslImpl implements ProductQueryDsl{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<List<Product>> findAllByCategory(String categoryId) {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        Optional<List<Product>> products = Optional.of(jpaQueryFactory.selectFrom(product)
                .innerJoin(category).on(category.categoryId.eq(product.category.categoryId)).fetchJoin()
                .where(product.category.categoryId.eq(categoryId))
                .fetch()
        );

        return products;
    }
}
