package com.yosep.product.product.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.category.data.entity.QCategory;
import com.yosep.product.product.data.entity.Product;
import com.yosep.product.product.data.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductQueryDslImpl implements ProductQueryDsl{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<List<Product>> findAllByCategoryId(Long categoryId) {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        Optional<List<Product>> products = Optional.of(jpaQueryFactory.selectFrom(product)
                .join(category)
                .on(product.category.id.eq(category.id))
                .fetchJoin()
                .fetch()
        );

        return products;
    }
}
