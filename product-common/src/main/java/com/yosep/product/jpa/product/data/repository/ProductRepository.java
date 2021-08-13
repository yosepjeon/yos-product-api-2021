package com.yosep.product.jpa.product.data.repository;

import com.yosep.product.jpa.product.data.dto.request.ProductTestDto;
import com.yosep.product.jpa.product.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, ProductQueryDsl {
}
