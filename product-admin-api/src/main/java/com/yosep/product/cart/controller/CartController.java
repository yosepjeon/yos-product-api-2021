package com.yosep.product.cart.controller;

import com.yosep.product.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity getCart(@RequestParam("userId") String userId) {
        cartService.readCartByUserId(userId);

        return ResponseEntity.ok().body("");
    }
}
