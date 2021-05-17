package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class CategoryRepositoryIntegrationTest extends BaseIntegrationTest {
    private final CategoryRepository categoryRepository;
    private long categoryId;

    @Autowired
    public CategoryRepositoryIntegrationTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    public void setUp() {
        Category category = Category.builder()
                .name("test0")
                .build();

        Category createdCategory = categoryRepository.save(category);
        categoryId = createdCategory.getId();
    }

    @Test
    @DisplayName("카테고리 생성 테스트")
    public void createCategoryTest() {
        log.info("카테고리 생성 테스트");
        Category category = Category.builder()
                .name("create-test1")
                .build();

        Category createdCategory = categoryRepository.save(category);
        log.info(createdCategory.toString());
        Assertions.assertEquals(true, createdCategory.equals(category));
    }

    @Test
    @DisplayName("카테고리 조회 성공 테스트")
    public void readCategoryByIdSuccessTest() {
        log.info("카테고리 조회 성공 테스트");
        Optional<Category> resultById = categoryRepository.findById(categoryId);

        log.info(resultById.toString());
        Assertions.assertEquals(true, resultById.isPresent());
    }

    @Test
    @DisplayName("카테고리 조회 실패 테스트")
    public void readCategoryFailTest() {
        log.info("카테고리 조회 실패 테스트");
        Optional<Category> resultById = categoryRepository.findById(Long.valueOf(-1));

        log.info(resultById.toString());
        Assertions.assertEquals(false, resultById.isPresent());
    }
}
