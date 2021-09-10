package com.yosep.product.category.data.repository;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseCategoryIntegrationTest;
import com.yosep.product.jpa.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.jpa.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.category.data.repository.CategoryRepositoryQueryDslImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CategoryQueryDslCategoryIntegrationTest extends BaseCategoryIntegrationTest {

    @Autowired
    public CategoryQueryDslCategoryIntegrationTest(CategoryRepository categoryRepository, CategoryRepositoryQueryDslImpl categoryRepositoryQueryDslImpl, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @BeforeEach
    public void setUp() {
        Category category = new Category();
        category.setCategoryId("create-test1");
        category.setName("create-test1");

        Category createdCategory = categoryRepository.save(category);
        categoryId3 = createdCategory.getCategoryId();

        Category category2 = new Category();
        category2.setCategoryId("create-test2");
        category2.setName("create-test1");

        Category createdCategory2 = categoryRepository.save(category2);

        log.info("parentId = " + categoryId3);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category-test" + i, categoryId3);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
        }
    }

    @Test
    @DisplayName("[CategoryQueryDsl] 카테고리 이름 기준 조회 성공 테스트")
    public void readCategoryByNameSuccessTest() {
        Optional<List<SelectedCategoryDto>> results = categoryRepository.findByName("create-test1");

        log.info("카테고리 이름 기준 조회 성공 테스트");
        List<SelectedCategoryDto> categories = results.orElse(Collections.unmodifiableList(new ArrayList<>()));
        Assertions.assertEquals(true, categories.size() > 1);
        log.info("categories: " + categories);
    }

    @Test
    @DisplayName("[CategoryQueryDsl] 카테고리 이름 기준 조회 실패 테스트")
    public void readCategoryByNameFailTest() {
        Optional<List<SelectedCategoryDto>> results = categoryRepository.findByName("test1");

        log.info("카테고리 이름 기준 조회 실패 테스트");
        List<SelectedCategoryDto> categories = results.orElse(Collections.unmodifiableList(new ArrayList<>()));
        Assertions.assertEquals(false, categories.size() > 0);
        log.info("categories: " + categories);
    }

    @Test
    @DisplayName("[CategoryQueryDsl] 부모-자식 카테고리 가져오기 성공 테스트")
    public void readCategoryByParentIsNullSuccessTest() {
        Optional<List<Category>> categorieDtos = categoryRepository.findAllByParentIsNull();

        log.info(categorieDtos.toString());
    }

//    @Test
//    @DisplayName("[CategoryQueryDsl] 카테고리가 Empty일 때 부모-자식 카테고리 가져오기 테스트")
//    public void readCategoryByParentIsNullFailTest() {
//        categoryRepository.deleteAll();
//        Optional<List<Category>> categorieDtos = categoryRepository.findAllByParentIsNull();
//
//        log.info(categorieDtos.toString());
//    }

    @Test
    @DisplayName("[CategoryQueryDsl] 자식이 가리키는 부모ID를 통해 카테고리 가져오기 성공 테스트")
    public void readCategoryByParentIsNotNullSuccessTest() {
        Optional<List<Category>> categorieDtos = categoryRepository.findAllByParentIsNull();

        log.info(categorieDtos.toString());
    }

//    @Test
//    @DisplayName("[CategoryQueryDsl] 카테고리가 Empty일 때 자식이 가리키는 부모ID를 통해 카테고리 가져오기 성공 테스트")
//    public void readCategoryByParentIsNotNullFailTest() {
//        categoryRepository.deleteAll();
//        Optional<List<Category>> categorieDtos = categoryRepository.findAllByParentIsNull();
//
//        log.info(categorieDtos.toString());
//    }
}
