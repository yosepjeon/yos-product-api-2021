package com.yosep.product.product.repository;

import com.yosep.product.common.data.Transaction;
import com.yosep.product.common.exception.InvalidStockValueException;
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
public class ReactiveProductRepositoryUpdateTest extends BaseProductRepositoryTest {

    @Autowired
    public ReactiveProductRepositoryUpdateTest(ReactiveProductRepository reactiveProductRepository) {
        this.reactiveProductRepository = reactiveProductRepository;
    }

    @Test
    @DisplayName("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 성공 테스트")
    public void 재고_감소_성공_테스트() {
        log.info("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 성공 테스트");
        final long[] prevStock = {-1};

        reactiveProductRepository.findById("product-select-test1")
                .flatMap(selectedProduct -> {
                    log.info("[조회 완료]");
                    log.info(selectedProduct.toString());
                    prevStock[0] = selectedProduct.getStockQuantity();

                    return selectedProduct.decreaseStock(1L, reactiveProductRepository);
                })
                .doOnError(Throwable::printStackTrace)
                .as(Transaction::withRollback)
                .as(StepVerifier::create)
                .assertNext(completedProduct -> {
                    Assertions.assertEquals(true, prevStock[0] != -1);
                    Assertions.assertEquals(true, completedProduct.getStockQuantity() == (prevStock[0] - 1));
                    log.info("[Test 완료]");
                    log.info(completedProduct.toString());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 실패 테스트")
    public void 재고_감소_실패_테스트() {
        log.info("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 실패 테스트");
        final long[] prevStock = {-1};

        Assertions.assertThrows(AssertionError.class, () -> {
            reactiveProductRepository.findById("product-select-test1")
                    .flatMap(selectedProduct -> {
                        log.info("[조회 완료]");
                        log.info(selectedProduct.toString());
                        prevStock[0] = selectedProduct.getStockQuantity();

                        return selectedProduct.decreaseStock(11, reactiveProductRepository);
                    })
                    .doOnError(throwable -> {
                        log.info("[InvalidStockValueException]");
                        log.info(throwable.toString());
                    })
                    .as(Transaction::withRollback)
                    .as(StepVerifier::create)
                    .assertNext(completedProduct -> {
                        log.info("여기까지 로직이 오지 않음 오면 이상현상임");
                    })
                    .verifyComplete();
        });
    }

    @Test
    @DisplayName("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 성공 테스트")
    public void 재고_증가_성공_테스트() {
        log.info("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 성공 테스트");
        final long[] prevStock = {-1};
        int value = 3;

        reactiveProductRepository.findById("product-select-test1")
                .flatMap(selectedProduct -> {
                    log.info("[조회 완료]");
                    log.info(selectedProduct.toString());
                    prevStock[0] = selectedProduct.getStockQuantity();

                    return selectedProduct.increaseStock(value, reactiveProductRepository);
                })
                .doOnError(throwable -> {
                    log.info("[InvalidStockValueException]");
                    log.info(throwable.toString());
                })
                .as(Transaction::withRollback)
                .as(StepVerifier::create)
                .assertNext(completedProduct -> {
                    Assertions.assertEquals(true, prevStock[0] != -1);
                    Assertions.assertEquals(true, completedProduct.getStockQuantity() == (prevStock[0] + value));
                    log.info("[Test 완료]");
                    log.info(completedProduct.toString());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 실패 테스트")
    public void 재고_증가_실패_테스트() {
        log.info("[ReactiveProductRepository Domain] 상품 도메인 재고 감소 실패 테스트");
        final long[] prevStock = {-1};
        int value = 3;
    }
}
