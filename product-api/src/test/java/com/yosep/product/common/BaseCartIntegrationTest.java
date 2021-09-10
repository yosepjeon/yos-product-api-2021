package com.yosep.product.common;

import com.yosep.product.cart.service.CartCommandService;
import com.yosep.product.cart.service.CartQueryService;
import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.cart.data.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class BaseCartIntegrationTest {
    protected CartRepository cartRepository;
    protected CartCommandService cartCommandService;
    protected CartQueryService cartQueryService;

    @BeforeEach
    public void setTestCart() {
        String userId = "cart-test-user1";
        String cartId = userId;

        Cart cart = new Cart(cartId, userId);

        Cart createdCart = cartRepository.save(cart);
//        cartRepository
    }

    @BeforeEach
    public void drawLineByTestBefore() {
        log.info("===================================================== START =====================================================");
    }

    @AfterEach
    public void drawLineByTestAfter(){
        log.info("===================================================== END =====================================================");
    }
}
