package com.yosep.product.jpa.product.data.repository;

import com.yosep.product.jpa.product.data.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductQueryDsl {
    Optional<List<Product>> findAllByCategory(String categoryId);
}
