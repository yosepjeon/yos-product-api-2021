package com.yosep.product.cart.service;

import com.yosep.product.jpa.cart.data.dto.SelectedCartDto;
import com.yosep.product.jpa.cart.data.entity.Cart;
import com.yosep.product.jpa.cart.data.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class CartQueryService {
    private final CartRepository cartRepository;

    public SelectedCartDto readCartByUserId(String userId) {
        Optional<Cart> selectedCart = cartRepository.findByUserId(userId);

        return selectedCart.isEmpty() ? new SelectedCartDto() : new SelectedCartDto(selectedCart.get());
    }
}
