package com.yosep.product.product.data.repository;

import com.yosep.product.common.BaseTest;
import com.yosep.product.product.data.entity.ProductTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class ProductTestQueryDslTest extends BaseTest {
    private final ProductTestRepositoryQueryDsl productTestRepositoryQueryDsl;

    @Autowired
    public ProductTestQueryDslTest(ProductTestRepositoryQueryDsl productTestRepositoryQueryDsl) {
        this.productTestRepositoryQueryDsl = productTestRepositoryQueryDsl;
    }

    @Test
    @DisplayName("QueryDsl 테스트")
    public void findByIdTest() {
        Optional<ProductTest> result = productTestRepositoryQueryDsl.findById("test");

        Assertions.assertEquals(true, result.isEmpty());
    }
}
