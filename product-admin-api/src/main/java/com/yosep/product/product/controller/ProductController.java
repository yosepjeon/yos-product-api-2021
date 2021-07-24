package com.yosep.product.product.controller;

import com.yosep.product.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity readProducts() {
        return null;
    }

    @GetMapping("/{category-id}")
    public ResponseEntity readProductsByCategory() {
        return null;
    }

    @GetMapping("/{product-id}")
    public ResponseEntity readProductById() {

        return null;
    }
}
