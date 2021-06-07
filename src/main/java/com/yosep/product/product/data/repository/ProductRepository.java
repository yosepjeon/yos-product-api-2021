package com.yosep.product.product.data.repository;

import com.yosep.product.product.data.dto.ProductTestDto;
import com.yosep.product.product.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, ProductQueryDsl {
    public List<ProductTestDto> findByCategoryId(long categoryId);
}
