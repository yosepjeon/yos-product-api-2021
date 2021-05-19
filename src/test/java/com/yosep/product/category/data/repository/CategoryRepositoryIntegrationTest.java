package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;
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
        Category category = new Category();
        category.setName("test0");

        Category createdCategory = categoryRepository.save(category);
        categoryId = createdCategory.getId();

        Category childCategory = new Category();
        childCategory.setName("test0-child");

        Category createdChildCategory = categoryRepository.save(childCategory);
        createdCategory.addChildCategory(createdChildCategory);
        categoryRepository.save(createdCategory);
    }

    @Test
    @DisplayName("카테고리 생성 테스트")
    public void createCategoryTest() {
        log.info("카테고리 생성 테스트");
//        Category category = Category.builder()
//                .name("create-test1")
//                .build();
        Category category = new Category();
        category.setName("create-test1");

        Category createdCategory = categoryRepository.save(category);
        log.info(createdCategory.toString());
        Assertions.assertEquals(true, createdCategory.equals(category));
    }

    @Test
    @DisplayName("카테고리 조회 성공 테스트")
    public void readCategoryByIdSuccessTest() {
        log.info("카테고리 조회 성공 테스트");
        Optional<Category> resultById = categoryRepository.findById(categoryId);
        Category category = resultById.get();
        SelectedCategoryDto selectedCategoryDto = new SelectedCategoryDto(category);

        log.info(selectedCategoryDto.toString());
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

    @Test
    @DisplayName("카테고리 전체 조회 테스트")
    public void readAllCategoriesTest() {
        log.info("카테고리 전체 조회 테스트");
        List<Category> categories = categoryRepository.findAll();

        categories.forEach((Category category) -> {
            log.info(category.toString());
        });

        Assertions.assertEquals(true, categories.size() > 0);
    }
}
