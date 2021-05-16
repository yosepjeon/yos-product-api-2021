package com.yosep.product.category.service;

import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.common.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CategoryServiceTest extends BaseTest {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public CategoryServiceTest(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
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
    @DisplayName("Category 생성 테스트")
    public void createCategoryTest() {


//        categoryService.createCategory(null);
    }
}
