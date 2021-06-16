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
    public Optional<List<Product>> findAllByCategoryId(Long categoryId) {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        Optional<List<Product>> products = Optional.of(jpaQueryFactory.selectFrom(product)
                .innerJoin(category)
                .on(product.category.id.eq(category.id))
                .where(product.category.id.eq(categoryId))
                .fetchJoin()
                .fetch()
        );

//        Optional<List<Product>> products = Optional.of(jpaQueryFactory.selectFrom(product)
//                .where(product.category.id.eq(categoryId))
//                .fetch()
//        );

        return products;
    }
}
