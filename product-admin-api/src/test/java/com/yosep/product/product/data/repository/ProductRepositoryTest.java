package com.yosep.product.product.data.repository;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseProductIntegrationTest;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.logic.RandomIdGenerator;
import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.response.CreatedProductDto;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.mapper.product.ProductMapper;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Collections;

@Slf4j
@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductRepositoryTest extends BaseProductIntegrationTest {

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Test
    @DisplayName("[Product Map Struct] 상품 저장 테스트")
    public void ProductDtoForCreation에서_Product_변환_테스트() {
        log.info("[Product Map Struct] ProductDtoForCreation에서 Product로 변환 테스트");

        ProductDtoForCreation productDtoForCreation = new ProductDtoForCreation(
                "test1",
                100000,
                100,
                "[Product Map Struct] ProductDtoForCreation에서 Product로 변환 테스트",
                childCategoryId1,
                Collections.emptyList()
        );
        productDtoForCreation.setProductId(RandomIdGenerator.createId());

        Product product = ProductMapper.INSTANCE.productDtoForCreationToProduct(productDtoForCreation);
        product.setCategory(categoryRepository.findById(productDtoForCreation.getCategory()).get());

        product = productRepository.save(product);

        CreatedProductDto createdProductDto = ProductMapper.INSTANCE.productToCreatedProductDto(product);

        log.info("생성 완료");
        log.info("productEntity: " + product.toString());
        log.info("createdProductDto: " + createdProductDto.toString());
    }
}
