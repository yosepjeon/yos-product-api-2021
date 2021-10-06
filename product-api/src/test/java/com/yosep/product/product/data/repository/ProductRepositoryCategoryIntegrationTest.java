package com.yosep.product.product.data.repository;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseProductIntegrationTest;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.entity.PageRequest;
import com.yosep.product.jpa.product.data.dto.SelectedProductDtoV2;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.entity.ProductTest;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import com.yosep.product.jpa.product.data.repository.ProductTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductRepositoryCategoryIntegrationTest extends BaseProductIntegrationTest {
    private final ProductTestRepository productTestRepository;

    @Autowired
    public ProductRepositoryCategoryIntegrationTest(CategoryService categoryService, CategoryRepository categoryRepository, ProductTestRepository productTestRepository, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productTestRepository = productTestRepository;
    }

    @Test
    @DisplayName("[ProductRepositoryCategoryIntegrationTest]데이터 연결 및 읽어오기 Test")
    public void readEntityTest() {
        log.info("[ProductRepositoryCategoryIntegrationTest] 데이터 연결 및 읽어오기 Test");
        Optional<ProductTest> productTest = productTestRepository.findById("test");

        Assertions.assertEquals(true,productTest.isEmpty());
    }

    @Test
    @DisplayName("[ProductRepositoryCategoryIntegrationTest] 카테고리별 전체 상품 읽어오기 Test")
    public void findAllTest() {
        log.info("[ProductRepositoryCategoryIntegrationTest] 카테고리별 전체 상품 읽어오기 Test");
        // Given & When
        List<Product> products = productRepository.findAllByCategory(childCategoryId1).get();

        // Then
        products.forEach(p -> {
            log.info("productId: " + p.getProductId().toString() + ", productName: " + p.getProductName() + ", category: " + p.getCategory().getName());
        });
    }

    @Test
    @DisplayName("[ProductRepositoryCategoryIntegrationTest] 카테고리별 페이지된 전체 상품 읽어오기 Test")
    public void 카테고리별_페이지된_전체_상품_읽어오기_Test() {
        log.info("[ProductRepositoryCategoryIntegrationTest] 카테고리별 페이지된 전체 상품 읽어오기 Test");
        // Given
        PageRequest pageRequest = new PageRequest();
        // When
        Optional<List<SelectedProductDtoV2>> optionalProducts =  productRepository.findAllByCategory(pageRequest.of(), "test-category1-child0");

        // Then
        Assertions.assertEquals(true, optionalProducts.isPresent());
    }

    @Test
    @DisplayName("특정 카테고리에 상품 저장 테스트")
    public void 특정_카테고리에_상품_저장_테스트() {
//        Category category = categoryRepository
//        productRepository.deleteAll();
    }
}
