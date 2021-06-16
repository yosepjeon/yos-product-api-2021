package com.yosep.product.jpa.cart.data.repository;

import com.yosep.product.jpa.cart.data.entity.Cart;

import java.util.Optional;

public interface CartRepositoryQueryDsl {
    Optional<Cart> findByUserId(String userId);
}
