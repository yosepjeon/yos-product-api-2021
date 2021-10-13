package com.yosep.product.jpa.cart.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.jpa.cart.data.dto.CartDto;
import com.yosep.product.jpa.cart.data.dto.QSelectedCartDto;
import com.yosep.product.jpa.cart.data.dto.SelectedCartDto;
import com.yosep.product.jpa.cart.data.dto.request.CartProductDto;
import com.yosep.product.jpa.cart.data.entity.Cart;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import static com.yosep.product.jpa.cart.data.entity.QCart.cart;

@RequiredArgsConstructor
public class CartRepositoryQueryDslImpl implements CartRepositoryQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Cart> findByUserId(String userId) {
        Cart cartEntity = jpaQueryFactory.selectFrom(cart)
                .distinct()
                .where(cart.userId.eq(userId))
                .fetchOne();

        return cartEntity != null ? Optional.of(cartEntity) : Optional.empty();
    }

    @Override
    public Optional<CartDto> findByUserIdV2(String userId) {
        SelectedCartDto selectedCart = jpaQueryFactory
                .select(
                        new QSelectedCartDto(
                                cart.cartId,
                                cart.userId
                        )
                )
                .distinct()
                .from(cart)
                .where(cart.userId.eq(userId))
                .fetchOne();

        Map<String, CartProductDto> products;

        return Optional.empty();
    }
}
