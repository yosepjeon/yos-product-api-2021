package com.yosep.product.category.service;

import com.yosep.product.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.common.BaseCategoryIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@Slf4j
public class CategoryServiceCategoryIntegrationTest extends BaseCategoryIntegrationTest {

    @Autowired
    public CategoryServiceCategoryIntegrationTest(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Test
    @DisplayName("[CategoryService] Category 생성 테스트")
    public void createCategoryTest() {

        log.info("카테고리 생성 테스트");
        CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category-test0", null);

        Optional<Category> createdCategory = categoryService.createCategory(categoryDtoForCreation);
        log.info(createdCategory.toString());
        Assertions.assertEquals(true, createdCategory.isPresent());
    }

    @Test
    @DisplayName("[CategoryService] 특정 카테고리를 읽어오되, 자식 카테고리들도 함께 읽어오기 테스트")
    public void readCategoryByIdTest() {
        log.info("특정 카테고리를 읽어오되, 자식 카테고리들도 함께 읽어오기 테스트");
        Optional<SelectedCategoryDto> readedCategoryDto = categoryService.readCategoryById(categoryId3);
        log.info(readedCategoryDto.toString());
        Assertions.assertEquals(true, readedCategoryDto.isPresent());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("[CategoryService] 부모 카테고리 단위 자식 카테고리 그룹화하여 읽어오기 성공 테스트")
    public void readCategoriesByParentIsNullSuccessTest() {
        log.info("전체 카테고리를 읽어오되, 부모 카테고리로 그룹화된 자식 카테고리 읽어오기 성공 테스트");

        Optional<CollectionModel<EntityModel<SelectedCategoryDto>>> categories = categoryService.readCategoriesByParentIsNullForUpdate();

        log.info(categories.toString());
        Assertions.assertEquals(true, categories.isPresent());
    }

    @Test
    @DisplayName("[CategoryService] 부모 카테고리 단위 자식 카테고리 그룹화하여 읽어오기 실패 테스트")
    public void readCategoriesByParentIsNullFailTest() {
        log.info("전체 카테고리를 읽어오되, 부모 카테고리로 그룹화된 자식 카테고리 읽어오기 실패 테스트");

        categoryService.deleteAllCategories();
        Optional<CollectionModel<EntityModel<SelectedCategoryDto>>> categories = categoryService.readCategoriesByParentIsNullForUpdate();

        log.info(categories.toString());
        Assertions.assertEquals(true, categories.isEmpty());
    }

    @Test
    @DisplayName("[CategoryService] 부모 카테고리 수정 성공 테스트")
    public void updateCategorySuccessTest() {
        log.info("[CategoryService] 카테고리 수정 성공 테스트");

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId1);
        Category category = optionalCategory.get();

        log.info("수정 전: " + category.toString());
        category.setName("enekelx1");

        log.info("수정 후: " + categoryRepository.findById(categoryId1).toString());
    }

    @Test
    @DisplayName("[CategoryService] 부모 카테고리 수정 실패 테스트")
    public void updateCategoryFailTest() {

    }
}
