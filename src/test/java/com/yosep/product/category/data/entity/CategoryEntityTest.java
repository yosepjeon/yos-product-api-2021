package com.yosep.product.category.data.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CategoryEntityTest {
    @Test
    @DisplayName("Category Entity 검사 테스트")
    public void categoryEntityTest() {
        Category category = Category.builder()
                .id("category-entity-test")
                .name("category entity test")
                .build();

        log.info("Category Entity 검사 테스트");
        log.info("Step1>> builder로 생성 시 parent=null Test");
        Assertions.assertEquals(true,category.getParent() == null);
        log.info("createdCategoryEntity: " + category.toString());
        log.info("END");
    }
}
