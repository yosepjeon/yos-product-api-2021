package com.yosep.product.product.data.repository;

import com.yosep.product.product.data.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductQueryDsl {
    Optional<List<Product>> findAllByCategoryId(Long categoryId);
}
