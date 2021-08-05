package com.yosep.product.product.controller;

import com.yosep.product.jpa.product.data.dto.request.ProductStepDtoForCreation;
import com.yosep.product.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping("/test")
    public ResponseEntity test(@RequestBody ProductStepDtoForCreation productStepDtoForCreation) {
        log.info("parsedData: " + productStepDtoForCreation.toString());

        try {

        }catch (RuntimeException rex) {

        }

        return ResponseEntity.ok(productStepDtoForCreation);
    }

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

    @PostMapping("/order-saga")
    public ResponseEntity processOrderToProductSaga(@RequestBody @Valid ProductStepDtoForCreation productStepDtoForCreation, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            productStepDtoForCreation = productService.processProductStep(productStepDtoForCreation);
            productStepDtoForCreation.setState("COMP");

            return ResponseEntity.ok(productStepDtoForCreation);
        } catch (RuntimeException runtimeException) {
            productStepDtoForCreation.setState(runtimeException.getClass().getSimpleName());

            return ResponseEntity.ok(productStepDtoForCreation);
        }
    }

}
