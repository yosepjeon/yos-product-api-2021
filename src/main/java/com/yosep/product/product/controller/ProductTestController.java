package com.yosep.product.product.controller;

import com.yosep.product.product.data.entity.ProductTest;
import com.yosep.product.product.service.ProductTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products-test")
@RestController
public class ProductTestController {
    private final ProductTestService productTestService;

    @Autowired
    public ProductTestController(ProductTestService productTestService) {
        this.productTestService = productTestService;
    }

    @RequestMapping("/rest-doc-test")
    public ResponseEntity<EntityModel<ProductTest>> testRestDoc() {
        ProductTest productTest = productTestService.readProductTest("test");
        EntityModel<ProductTest> productTestResource = EntityModel.of(productTest);

        return ResponseEntity.ok(productTestResource);
    }
}
