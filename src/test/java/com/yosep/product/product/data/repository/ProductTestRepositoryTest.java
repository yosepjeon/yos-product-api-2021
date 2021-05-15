package com.yosep.product.product.data.repository;

import com.yosep.product.product.data.entity.ProductTest;
import com.yosep.product.product.data.repository.ProductTestRepository;
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
public class ProductTestRepositoryTest {
    private ProductTestRepository productTestRepository;

    @Autowired
    public ProductTestRepositoryTest(ProductTestRepository productTestRepository) {
        this.productTestRepository = productTestRepository;
    }

    @Test
    @DisplayName("데이터 연결 및 읽어오기 Test")
    public void readEntityTest() {
        Optional<ProductTest> productTest = productTestRepository.findById("test");

        Assertions.assertEquals(true,productTest.isEmpty());
    }
}
