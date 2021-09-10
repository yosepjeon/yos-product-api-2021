package com.yosep.product.cart.service;


import com.yosep.product.common.BaseCartIntegrationTest;
import com.yosep.product.jpa.cart.data.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CartCommandServiceTest extends BaseCartIntegrationTest {
    @Autowired
    public CartCommandServiceTest(CartRepository cartRepository, CartCommandService cartCommandService, CartQueryService cartQueryService) {
        this.cartQueryService = cartQueryService;
        this.cartCommandService = cartCommandService;
        this.cartRepository = cartRepository;
    }

    @Test
    @DisplayName("[Cart Service] 장바구니 생성 성공 테스트")
    public void 장바구니_생성_성공_테스트() {
        log.info("[Cart Service] 장바구니 생성 성공 테스트");
        String userId = "cart-create-test1";

        boolean result = cartCommandService.createCart(userId);

        Assertions.assertEquals(true, result);
    }

    @Test
    @DisplayName("[Cart Service] 장바구니 생성 실패 테스트")
    public void 장바구니_생성_실패_테스트() {
        log.info("[Cart Service] 장바구니 생성 실패 테스트");
        String userId = "cart-test-user1";

        boolean result = cartCommandService.createCart(userId);

        Assertions.assertEquals(false, result);
    }


}
