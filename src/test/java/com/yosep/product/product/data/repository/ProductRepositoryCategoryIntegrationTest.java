package com.yosep.product.product.data.repository;

import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.BaseProductIntegrationTest;
import com.yosep.product.product.data.entity.ProductTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class ProductRepositoryCategoryIntegrationTest extends BaseProductIntegrationTest {
    private final ProductTestRepository productTestRepository;

    @Autowired
    public ProductRepositoryCategoryIntegrationTest(ProductTestRepository productTestRepository) {
        this.productTestRepository = productTestRepository;
    }

    @Test
    @DisplayName("데이터 연결 및 읽어오기 Test")
    public void readEntityTest() {
        Optional<ProductTest> productTest = productTestRepository.findById("test");

        Assertions.assertEquals(true,productTest.isEmpty());
    }

    @Test
    @DisplayName("특정 카테고리에 상품 저장 테스트")
    public void 특정_카테고리에_상품_저장_테스트() {
        Category category = Category.builder()
                .id(childCategoryId1)
//                .name()
                .build();
    }
}
