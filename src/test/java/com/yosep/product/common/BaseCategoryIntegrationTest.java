package com.yosep.product.common;

import com.yosep.product.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class BaseCategoryIntegrationTest {
    protected CategoryRepository categoryRepository;
    protected CategoryService categoryService;
    protected long categoryId1;
    protected long categoryId2;
    protected long categoryId3;

    @BeforeEach
    public void drawLineByTestBefore() {
        log.info("===================================================== START =====================================================");
    }

    @BeforeEach
    public void createParentAndChildsCategories() {
        Category category1 = new Category();
        category1.setName("create-category-parent1");

        Category createdCategory1 = categoryRepository.save(category1);
        categoryId1 = createdCategory1.getId();


        log.info("parentId = " + categoryId1);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category1-child" + i, categoryId1);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
        }

        Category category2 = new Category();
        category2.setName("create-category-parent2");

        Category createdCategory2 = categoryRepository.save(category2);
        categoryId2 = createdCategory2.getId();

        log.info("parentId = " + categoryId2);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category2-child" + i, categoryId2);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
        }

        Category category3 = new Category();
        category3.setName("create-category-parent3");

        Category createdCategory3 = categoryRepository.save(category3);
        categoryId3 = createdCategory3.getId();

        log.info("parentId = " + categoryId3);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category3-child" + i, categoryId3);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
        }
    }

    @AfterEach
    public void drawLineByTestAfter(){
        log.info("===================================================== END =====================================================");
    }
}
