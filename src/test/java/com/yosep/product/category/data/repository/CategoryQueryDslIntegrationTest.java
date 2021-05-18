package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CategoryQueryDslIntegrationTest extends BaseIntegrationTest {
    private final CategoryRepositoryQueryDsl queryDsl;
    private final CategoryRepository categoryRepository;
    private long categoryId;

    @Autowired
    public CategoryQueryDslIntegrationTest(CategoryRepositoryQueryDsl queryDsl, CategoryRepository categoryRepository) {
        this.queryDsl = queryDsl;
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    public void setUp() {
//        Category category = Category.builder()
//                .name("test0")
//                .build();
        Category category = new Category();
        category.setName("create-test1");

        Category createdCategory = categoryRepository.save(category);
        categoryId = createdCategory.getId();
    }

    @Test
    @DisplayName("카테고리 이름 기준 조회 성공 테스트")
    public void readCategoryByNameSuccessTest() {
        for (int i = 0; i < 3; i++) {
//            Category category = Category.builder()
//                    .name("test0")
//                    .build();
            Category category = new Category();
            category.setName("create-test1");

            Category createdCategory = categoryRepository.save(category);
        }

        Optional<List<Category>> results = queryDsl.findByName("create-test1");

        log.info("카테고리 이름 기준 조회 성공 테스트");
        List<Category> categories = results.orElse(Collections.unmodifiableList(new ArrayList<>()));
        Assertions.assertEquals(true, categories.size() > 1);
        log.info("categories: " + categories);
    }

    @Test
    @DisplayName("카테고리 이름 기준 조회 실패 테스트")
    public void readCategoryByNameFailTest() {
        for (int i = 0; i < 3; i++) {
//            Category category = Category.builder()
//                    .name("test0")
//                    .build();
            Category category = new Category();
            category.setName("create-test1");

            Category createdCategory = categoryRepository.save(category);
        }

        Optional<List<Category>> results = queryDsl.findByName("test1");

        log.info("카테고리 이름 기준 조회 실패 테스트");
        List<Category> categories = results.orElse(Collections.unmodifiableList(new ArrayList<>()));
        Assertions.assertEquals(false, categories.size() > 0);
        log.info("categories: " + categories);
    }
}
