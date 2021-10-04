package com.yosep.product.product.controller;

import com.yosep.product.jpa.common.entity.PageRequest;
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
    public ResponseEntity checkIsPresentProduct(@RequestParam(value = "id") String productId) {
        return ResponseEntity.ok(productService.checkIsPresentProduct(productId));
    }

    @GetMapping("/{category-id}")
    public ResponseEntity readProductsByCategory(final PageRequest pageRequest, @RequestParam("categoryId") String categoryId)  {
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
            System.out.println(productStepDtoForCreation.toString());

            return ResponseEntity.ok(productStepDtoForCreation);
        } catch (RuntimeException runtimeException) {
            productStepDtoForCreation.setState("EXCEPTION");
            productStepDtoForCreation = productService.validateSagaProductDtos(productStepDtoForCreation);
            productStepDtoForCreation.setState("FAIL");
            System.out.println(productStepDtoForCreation.toString());

            return ResponseEntity.ok(productStepDtoForCreation);
        }
    }

    @PostMapping("/order-saga-revert")
    public ResponseEntity revertOrderToProductSaga(@RequestBody @Valid ProductStepDtoForCreation productStepDtoForCreation, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            productStepDtoForCreation = productService.processProductStep(productStepDtoForCreation);
            productStepDtoForCreation.setState("COMP");
            System.out.println(productStepDtoForCreation.toString());

            return ResponseEntity.ok(productStepDtoForCreation);
        } catch (RuntimeException runtimeException) {
            productStepDtoForCreation.setState("EXCEPTION");
            productStepDtoForCreation = productService.validateSagaProductDtos(productStepDtoForCreation);
            productStepDtoForCreation.setState("FAIL");
            System.out.println(productStepDtoForCreation.toString());

            return ResponseEntity.ok(productStepDtoForCreation);
        }
    }
}
