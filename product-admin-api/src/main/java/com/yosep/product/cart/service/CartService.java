package com.yosep.product.cart.service;

import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.cart.data.repository.CartRepository;
import com.yosep.product.jpa.common.logic.RandomIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    /*
    * 장바구니 생성
    * Logic:
    * 1. cartId를 생성하여 해당 ID의 장바구니가 있는지 검사
    * 1-1. 장바구니 존재한다면 1번 반복.
    * 1-2. 장바구니가 존재하지 않는다면 2번으로 이동
    * 2. 장바구니 생성 및 해당 엔티티 반환
     */
    @Transactional(readOnly = false)
    public Cart createCart(String userId) {
        String cartId = RandomIdGenerator.createId();

        while(true) {
            if(cartRepository.existsById(cartId)) {
                cartId = RandomIdGenerator.createId();
                continue;
            }else {
                Cart cart = new Cart(cartId, userId);

                return cartRepository.save(cart);
            }
        }
    }

    public void readCartByUserId(String userId) {

    }

//    @Transactional(readOnly = false)
//    public Cart addProductToCart(CartProductDto cartProductDto) {
//
//    }
}
