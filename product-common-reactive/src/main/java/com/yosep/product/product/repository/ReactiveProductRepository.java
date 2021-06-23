package com.yosep.product.product.repository;

import com.yosep.product.product.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface ReactiveProductRepository extends R2dbcRepository<Product, String> {
    //    @Query("SELECT * FROM yos_product ORDER BY created_date DESC OFFSET :offset LIMIT :limit")
    @Query("SELECT * FROM yos_product ORDER BY created_date LIMIT :limit OFFSET :offset")
    Flux<Product> findAllByPage(int offset, int limit);

    @Query("SELECT * FROM yos_product WHERE category_id :categoryId ORDER BY created_date LIMIT :limit OFFSET :offset")
    Flux<Product> findAllByPageAndA(long categoryId, int offset, int limit);

    @Query("SELECT * FROM yos_product WHERE product_name LIKE '%:productName%' ORDER BY created_date LIMIT :limit OFFSET :offset")
    Flux<Product> findAllByPageAndB(String productName ,int offset, int limit);

    @Query("SELECT * FROM yos_product ORDER BY created_date LIMIT :limit OFFSET :offset")
    Flux<Product> findAllByPageAndC(int offset, int limit);
}
