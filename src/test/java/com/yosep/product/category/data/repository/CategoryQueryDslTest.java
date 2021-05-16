package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CategoryQueryDslTest extends BaseTest {
    private final CategoryRepositoryQueryDsl queryDsl;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryQueryDslTest(CategoryRepositoryQueryDsl queryDsl, CategoryRepository categoryRepository) {
        this.queryDsl = queryDsl;
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    public void setUp() {
        Category category = Category.builder()
                .id("category-test0")
                .name("test0")
                .build();

        Category createdCategory = categoryRepository.save(category);
    }

    @Test
    @DisplayName("카테코리 이름 기준 Read Test")
    public void readCategoryByNameTest() {
        Category category = Category.builder()
                .id("category-test1")
                .name("test0")
                .build();

        Category createdCategory = categoryRepository.save(category);

        Optional<List<Category>> results = queryDsl.findByName("test0");

        log.info("Category Read By Name Test");
        List<Category> categories = results.orElse(Collections.unmodifiableList(new ArrayList<>()));
        Assertions.assertEquals(true, categories.size() == 2);
        log.info("categories: " + categories);
    }
}
