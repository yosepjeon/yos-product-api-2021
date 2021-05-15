package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CategoryRepositoryTest {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
    }
}
