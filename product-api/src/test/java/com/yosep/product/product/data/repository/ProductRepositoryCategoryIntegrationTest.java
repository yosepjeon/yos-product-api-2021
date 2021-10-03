package com.yosep.product.product.data.repository;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseProductIntegrationTest;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.entity.ProductTest;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import com.yosep.product.jpa.product.data.repository.ProductTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    @DisplayName("데이터 연결 및 읽어오기 Test")
    public void readEntityTest() {
        Optional<ProductTest> productTest = productTestRepository.findById("test");

        Assertions.assertEquals(true,productTest.isEmpty());
    }

    @Test
    @DisplayName("")
    public void findAllTest() {
//        List<ProductTestDto> products = productRepository.findByCategoryId(childCategoryId1);
//
//        products.forEach(p -> {
//            log.info(p.getCategory().toString());
//        });

        List<Product> products = productRepository.findAllByCategory(childCategoryId1).get();

        products.forEach(p -> {
            log.info(p.getProductId().toString());
        });
    }

    @Test
    @DisplayName("특정 카테고리에 상품 저장 테스트")
    public void 특정_카테고리에_상품_저장_테스트() {
//        Category category = categoryRepository
//        productRepository.deleteAll();
    }
}
