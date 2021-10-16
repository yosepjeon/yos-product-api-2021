package com.yosep.product.jpa.product.data.repository;

import com.yosep.product.jpa.product.data.dto.SelectedProductDtoForDetailPage;
import com.yosep.product.jpa.product.data.dto.SelectedProductDtoV2;
import com.yosep.product.jpa.product.data.entity.Product;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProductQueryDsl {
    Optional<SelectedProductDtoForDetailPage> findByIdForDetailPage(String productId);
    Optional<List<SelectedProductDtoV2>> findAllByCategory(PageRequest pageRequest, String categoryId);
    Optional<List<Product>> findAllByCategory(String categoryId);
}
