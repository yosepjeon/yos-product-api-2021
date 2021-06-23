package com.yosep.product.product.repository;

import com.yosep.product.common.data.Transaction;
import com.yosep.product.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ReactiveProductRepositoryReadTest extends BaseProductRepositoryTest {
    @Autowired
    public ReactiveProductRepositoryReadTest(ReactiveProductRepository reactiveProductRepository) {
        this.reactiveProductRepository = reactiveProductRepository;
    }

    @Test
    @DisplayName("[ReactiveProductRepository] 상품 조회 테스트")
    public void 상품_조회_테스트() {
        log.info("[ReactiveProductRepository] 상품 조회 테스트");

        Product productForCreation = new Product(
                "product-select-test0",
                "product-select-test0",
                3000L,
                10L,
                "detail",
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        productForCreation.setAsNew();

        reactiveProductRepository.save(productForCreation)
                .flatMap(createdProductForCreation -> {
                    log.info("[생성 완료]");
                    log.info(createdProductForCreation.toString());
                    return reactiveProductRepository.findById(createdProductForCreation.getProductId());
                })
                .as(Transaction::withRollback)
                .as(StepVerifier::create)
                .assertNext(selectedProductForCreation -> {
                    log.info("[Test 완료]");
                    log.info(selectedProductForCreation.toString());
                    Assertions.assertEquals(true, selectedProductForCreation != null);
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("상품 조회 페이징 테스트")
    public void 상품_조회_페이징_테스트() {
        log.info("[ReactiveProductRepository] 상품 조회 페이징 테스트");

        reactiveProductRepository.findAllByPage(0, 9)
                .buffer(9)
                .as(StepVerifier::create)
                .assertNext(products -> {
                    log.info("[조회 완료]");
                    log.info("product size: " + products.size());

                    products.forEach(product -> {
                        log.info(product.toString());
                    });
                })
                .verifyComplete();
    }

}
