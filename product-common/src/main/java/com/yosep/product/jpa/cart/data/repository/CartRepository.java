package com.yosep.product.jpa.cart.data.repository;

import com.yosep.product.jpa.cart.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String>, CartRepositoryQueryDsl {
}
