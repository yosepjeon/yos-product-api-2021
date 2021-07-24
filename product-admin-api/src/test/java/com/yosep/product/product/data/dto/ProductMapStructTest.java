package com.yosep.product.product.data.dto;

import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.mapper.product.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductMapStructTest {

    @Test
    @DisplayName("[Product Map Struct] ProductDtoForCreation에서 Product로 변환 테스트")
    public void ProductDtoForCreation에서_Product_변환_테스트() {
        log.info("[Product Map Struct] ProductDtoForCreation에서 Product로 변환 테스트");

//        ProductDtoForCreation productDtoForCreation = new ProductDtoForCreation(
//                "test1",
//                100000,
//                100,
//                "[Product Map Struct] ProductDtoForCreation에서 Product로 변환 테스트",
//                0,
//                Collections.emptyList()
//        );
//
//        Product product =  ProductMapper.INSTANCE.productDtoForCreationToProduct(productDtoForCreation);
    }
}
