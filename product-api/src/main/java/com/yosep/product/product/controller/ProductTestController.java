package com.yosep.product.product.controller;

import com.yosep.product.jpa.product.data.entity.ProductTest;
import com.yosep.product.product.service.ProductQueryService;
import com.yosep.product.product.service.ProductTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products-test")
@RestController
public class ProductTestController {
    private final ProductTestService productTestService;
    private final ProductQueryService productQueryService;

    @Autowired
    public ProductTestController(ProductTestService productTestService, ProductQueryService productQueryService) {
        this.productTestService = productTestService;
        this.productQueryService = productQueryService;
    }

    @GetMapping("/rest-doc-test")
    public ResponseEntity<EntityModel<ProductTest>> testRestDoc() {
        ProductTest productTest = productTestService.readProductTest("test");
        EntityModel<ProductTest> productTestResource = EntityModel.of(productTest);

        return ResponseEntity.ok(productTestResource);
    }
}
