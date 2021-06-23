package com.yosep.product.product.service;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseProductIntegrationTest;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import com.yosep.product.jpa.product.data.repository.ProductTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ProductServiceTest extends BaseProductIntegrationTest {
    @Autowired
    public ProductServiceTest(CategoryService categoryService, CategoryRepository categoryRepository, ProductTestRepository productTestRepository, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Test
    @DisplayName("[Product Entity] 상품 도메인 삭제 테스트")
    public void 상품_도메인_삭제_테스트() {
        productRepository.findById("");
    }
}
