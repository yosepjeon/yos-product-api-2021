package com.yosep.product.category.data.repository;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseCategoryIntegrationTest;
import com.yosep.product.jpa.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class CategoryRepositoryCategoryIntegrationTest extends BaseCategoryIntegrationTest {

    @Autowired
    public CategoryRepositoryCategoryIntegrationTest(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Test
    @DisplayName("[CategoryRepository] 카테고리 생성 테스트")
    public void createCategoryTest() {
        log.info("카테고리 생성 테스트");
//        Category category = Category.builder()
//                .name("create-test1")
//                .build();
        Category category = new Category();
        category.setCategoryId("create-test1");
        category.setName("create-test1");

        Category createdCategory = categoryRepository.save(category);
        log.info(createdCategory.toString());
        Assertions.assertEquals(true, createdCategory.equals(category));
    }

    @Test
    @DisplayName("[CategoryRepository] 카테고리 조회 성공 테스트")
    public void readCategoryByIdSuccessTest() {
        log.info("카테고리 조회 성공 테스트");
        Optional<Category> resultById = categoryRepository.findById(categoryId3);
        Category category = resultById.get();
        SelectedCategoryDto selectedCategoryDto = new SelectedCategoryDto(category);

        log.info(selectedCategoryDto.toString());
        Assertions.assertEquals(true, resultById.isPresent());
    }

    @Test
    @DisplayName("[CategoryRepository] 카테고리 조회 실패 테스트")
    public void readCategoryFailTest() {
        log.info("카테고리 조회 실패 테스트");
        Optional<Category> resultById = categoryRepository.findById("empty");

        log.info(resultById.toString());
        Assertions.assertEquals(false, resultById.isPresent());
    }

    @Test
    @DisplayName("[CategoryRepository] 카테고리 전체 조회 테스트")
    public void readAllCategoriesTest() {
        log.info("카테고리 전체 조회 테스트");
        List<Category> categories = categoryRepository.findAll();

        categories.forEach((Category category) -> {
            log.info(category.toString());
        });

        Assertions.assertEquals(true, categories.size() > 0);
    }
}
