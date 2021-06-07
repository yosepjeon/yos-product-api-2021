package com.yosep.product.common;

import com.yosep.product.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.category.service.CategoryService;
import com.yosep.product.product.data.entity.Product;
import com.yosep.product.product.data.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class BaseProductIntegrationTest {
    protected CategoryRepository categoryRepository;
    protected CategoryService categoryService;
    protected long categoryId1;
    protected long categoryId2;
    protected long categoryId3;
    protected long childCategoryId1;
    protected long childCategoryId2;
    protected long childCategoryId3;
    protected ProductRepository productRepository;

    @BeforeEach
    public void drawLineByTestBefore() {
        log.info("===================================================== START =====================================================");
    }

    @BeforeEach
    public void createCategoriesAndProducts() throws NoSuchAlgorithmException {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        double value = rand.nextDouble();

        Category category1 = new Category();
        category1.setName("create-category-parent1");

        Category createdCategory1 = categoryRepository.save(category1);
        categoryId1 = createdCategory1.getId();

        log.info("parentId = " + categoryId1);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category1-child" + i, categoryId1);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId1 = result.get().getId();
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
            childCategoryId2 = result.get().getId();
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
            childCategoryId3 = result.get().getId();
        }

        Category childCategory1 = categoryRepository.findById(childCategoryId1).get();

        log.info("상품 생성");
        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("create-product-category1-" + i)
                    .productName("product" + i)
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory1)
                    .build();

            productRepository.save(product);
        }

        Category childCategory2 = categoryRepository.findById(childCategoryId2).get();

        log.info("상품 생성");
        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("create-product-category2-" + i)
                    .productName("product" + i)
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory2)
                    .build();

            productRepository.save(product);
        }

        Category childCategory3 = categoryRepository.findById(childCategoryId3).get();

        log.info("상품 생성");
        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("create-product-category3-" + i)
                    .productName("product" + i)
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory3)
                    .build();

            productRepository.save(product);
        }
    }

    @AfterEach
    public void drawLineByTestAfter() {
        log.info("===================================================== END =====================================================");
    }
}
