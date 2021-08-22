package com.yosep.product.cart.data.repository;

import com.yosep.product.cart.service.CartCommandService;
import com.yosep.product.cart.service.CartQueryService;
import com.yosep.product.common.BaseCartIntegrationTest;
import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.cart.data.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class CartRepositoryQueryDslTest extends BaseCartIntegrationTest {
    @Autowired
    public CartRepositoryQueryDslTest(CartRepository cartRepository, CartCommandService cartCommandService, CartQueryService cartQueryService) {
        this.cartRepository = cartRepository;
        this.cartQueryService = cartQueryService;
        this.cartCommandService = cartCommandService;
    }

    @Test
    @DisplayName("[CartQuerydsl] 장바구니 조회 성공 테스트")
    public void 장바구니_조회_성공_테스트() {
        log.info("[CartQuerydsl] 장바구니 조회 성공 테스트");
        Optional<Cart> optionalCart = cartRepository.findByUserId("test-user1");

        Assertions.assertEquals(true, optionalCart.isPresent());
        Cart selectedCart = optionalCart.get();
        Assertions.assertEquals("test-user1", selectedCart.getCartId());
        Assertions.assertEquals("test-user1", selectedCart.getUserId());
    }


}
