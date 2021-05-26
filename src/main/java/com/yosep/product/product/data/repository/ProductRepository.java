package com.yosep.product.product.data.repository;

import com.yosep.product.product.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>, ProductQueryDsl {
}
