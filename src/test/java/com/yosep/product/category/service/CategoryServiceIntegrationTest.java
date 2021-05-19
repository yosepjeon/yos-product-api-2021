package com.yosep.product.category.service;

import com.yosep.product.category.data.dto.request.CategoryDto;
import com.yosep.product.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.common.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class CategoryServiceIntegrationTest extends BaseIntegrationTest {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private long categoryId;

    @Autowired
    public CategoryServiceIntegrationTest(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @BeforeEach
    public void setUp() {
        Category category = new Category();
        category.setName("test0");

        Category createdCategory = categoryRepository.save(category);
        categoryId = createdCategory.getId();
    }

    @Test
    @DisplayName("[CategoryService] Category 생성 테스트")
    public void createCategoryTest() {

        log.info("카테고리 생성 테스트");
        CategoryDto categoryDto = new CategoryDto("create-category-test0", null);

        Optional<Category> createdCategory = categoryService.createCategory(categoryDto);
        log.info(createdCategory.toString());
        Assertions.assertEquals(true, createdCategory.isPresent());
    }

    @Test
    @DisplayName("[CategoryService] 특정 카테고리를 읽어오되, 자식 카테고리들도 함께 읽어오기 테스트")
    public void readCategoryByIdTest() {
        log.info("특정 카테고리를 읽어오되, 자식 카테고리들도 함께 읽어오기 테스트");

        log.info("부모 카테고리 생성");
        CategoryDto categoryDto = new CategoryDto("create-category-test0", null);
        Category parentCategory = categoryService.createCategory(categoryDto).get();

        log.info("parentId = " + parentCategory.getId());
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            categoryDto = new CategoryDto("create-category-test1", parentCategory.getId());
            Optional<Category> result = categoryService.createCategory(categoryDto);
        }

        log.info("특정 카테고리를 읽어오되, 자식 카테고리들도 함께 읽어오기");
        Optional<SelectedCategoryDto> readedCategoryDto = categoryService.readCategoryById(parentCategory.getId());
        log.info(readedCategoryDto.toString());
        Assertions.assertEquals(true, readedCategoryDto.isPresent());
    }
}
