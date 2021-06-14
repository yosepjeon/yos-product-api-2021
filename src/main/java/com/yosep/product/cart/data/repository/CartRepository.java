package com.yosep.product.cart.data.repository;

import com.yosep.product.cart.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
