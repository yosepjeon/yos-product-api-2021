package com.yosep.product.product.repository;

import com.yosep.product.product.entity.ProductForCreation;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReactiveProductRepository extends R2dbcRepository<ProductForCreation, String> {
}
