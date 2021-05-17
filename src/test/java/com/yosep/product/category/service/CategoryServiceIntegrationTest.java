package com.yosep.product.category.service;

import com.yosep.product.category.data.dto.request.CategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.common.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

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
        Category category = Category.builder()
                .name("test0")
                .build();

        Category createdCategory = categoryRepository.save(category);
        categoryId = createdCategory.getId();
    }

    @Test
    @DisplayName("Category 생성 테스트")
    public void createCategoryTest() {
        CategoryDto categoryDto = new CategoryDto("category-test0", null);

//        categoryService.createCategory(null);
    }
}
