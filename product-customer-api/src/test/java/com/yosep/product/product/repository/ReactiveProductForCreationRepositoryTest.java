package com.yosep.product.product.repository;

import com.yosep.product.common.data.Transaction;
import com.yosep.product.product.entity.ProductForCreation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ReactiveProductForCreationRepositoryTest {
    private final ReactiveProductRepository reactiveProductRepository;

    @Autowired
    public ReactiveProductForCreationRepositoryTest(ReactiveProductRepository reactiveProductRepository) {
        this.reactiveProductRepository = reactiveProductRepository;
    }

    @Test
    @DisplayName("[ReactiveProductRepository] 상품 조회 테스트")
    public void 상품_조회_테스트() {
        log.info("[ReactiveProductRepository] 상품 조회 테스트");

        ProductForCreation productForCreation = new ProductForCreation(
                "product-select-test1",
                "product-select-test1",
                3000L,
                10L,
                "detail",
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        reactiveProductRepository.save(productForCreation)
                .flatMap(createdProductForCreation -> {
                    log.info("[생성 완료]");
                    log.info(createdProductForCreation.toString());
                    return reactiveProductRepository.findById(createdProductForCreation.getProductId());
                })
                .as(Transaction::withRollback)
                .as(StepVerifier::create)
                .assertNext(selectedProductForCreation -> {
                    log.info("[연산 완료]");
                    Assertions.assertEquals(true, selectedProductForCreation != null);
                })
                .verifyComplete();
    }
}
