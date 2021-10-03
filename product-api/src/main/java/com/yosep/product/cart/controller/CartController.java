package com.yosep.product.cart.controller;

import com.yosep.product.cart.service.CartCommandService;
import com.yosep.product.cart.service.CartQueryService;
import com.yosep.product.jpa.cart.data.dto.SelectedCartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartCommandService cartCommandService;
    private final CartQueryService cartQueryService;

    @GetMapping
    public ResponseEntity getCart(@RequestParam("userId") String userId) {
        SelectedCartDto selectedCartDto = cartQueryService.readCartByUserId(userId);

        return ResponseEntity.ok().body(selectedCartDto.getCartId().equals(userId));
    }
}
