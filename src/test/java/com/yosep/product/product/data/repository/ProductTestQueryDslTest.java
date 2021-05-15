package com.yosep.product.product.data.repository;

import com.yosep.product.product.data.entity.ProductTest;
import com.yosep.product.product.data.repository.ProductTestRepositoryQueryDsl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductTestQueryDslTest {
    private ProductTestRepositoryQueryDsl productTestRepositoryQueryDsl;

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
