package com.yosep.product.product.repository;

import com.yosep.product.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class BaseProductRepositoryTest {
    protected ReactiveProductRepository reactiveProductRepository;

    @BeforeEach
    public void drawLineByTestBefore() {
        log.info("===================================================== START =====================================================");
    }

    @BeforeEach
    public void setBeforeTest() {
        Product productForCreation = new Product(
                "product-select-test1",
                "product-select-test1",
                3000L,
                10L,
                "detail",
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        productForCreation.setAsNew();

        reactiveProductRepository.save(productForCreation)
                .block();

    }

    @AfterEach
    public void setAfterTest() {
        reactiveProductRepository.deleteAll()
                .block();
    }

    @AfterEach
    public void drawLineByTestAfter() {
        log.info("===================================================== END =====================================================");
    }
}
