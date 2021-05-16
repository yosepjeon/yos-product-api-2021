package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class CategoryRepositoryTest extends BaseTest {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
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
    @DisplayName("카테고리 생성 테스트")
    public void createCategoryTest() {

        log.info("Category Create Test");
        Category category = Category.builder()
                .id("category-test1")
                .name("test1")
                .build();

        Category createdCategory = categoryRepository.save(category);
        Assertions.assertEquals(true, createdCategory.equals(category));
        log.info("END");
    }

    @Test
    @DisplayName("카테고리 조회 성공 테스트")
    public void readCategorySuccessTest() {
        log.info("Category Read Success Test");
        String categoryId = "category-test0";

        Optional<Category> resultById = categoryRepository.findById(categoryId);
    }

    @Test
    @DisplayName("카테고리 조회 실패 테스트")
    public void readCategoryFailTest() {
        log.info("Category Read Fail Test");
        String categoryId = "category-test0";

    }
}
