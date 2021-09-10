package com.yosep.product.common;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.jpa.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.entity.ProductDiscount;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
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
    protected ProductRepository productRepository;
    //    protected long parentCategoryId1;
//    protected long parentCategoryId2;
//    protected long parentCategoryId3;
//    protected long childCategoryId1;
//    protected long childCategoryId2;
//    protected long childCategoryId3;
    protected String parentCategoryId1;
    protected String parentCategoryId2;
    protected String parentCategoryId3;
    protected String childCategoryId1;
    protected String childCategoryId2;
    protected String childCategoryId3;

    @BeforeEach
    public void drawLineByTestBefore() {
        log.info("===================================================== START =====================================================");
    }

    @BeforeEach
    public void createCategoriesAndProducts() throws NoSuchAlgorithmException {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        double value = rand.nextDouble();

        Category category1 = new Category();
        category1.setCategoryId("create-category-parent1");
        category1.setName("create-category-parent1");

        Category createdCategory1 = categoryRepository.save(category1);
        parentCategoryId1 = createdCategory1.getCategoryId();

        log.info("parentId = " + parentCategoryId1);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category1-child" + i, parentCategoryId1);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId1 = result.get().getCategoryId();
        }

        Category category2 = new Category();
        category2.setCategoryId("create-category-parent2");
        category2.setName("create-category-parent2");

        Category createdCategory2 = categoryRepository.save(category2);
        parentCategoryId2 = createdCategory2.getCategoryId();

        log.info("parentId = " + parentCategoryId2);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category2-child" + i, parentCategoryId2);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId2 = result.get().getCategoryId();
        }

        Category category3 = new Category();
        category3.setCategoryId("create-category-parent3");
        category3.setName("create-category-parent3");

        Category createdCategory3 = categoryRepository.save(category3);
        parentCategoryId3 = createdCategory3.getCategoryId();

        log.info("parentId = " + parentCategoryId3);
        log.info("자식 카테고리 생성");
        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("create-category3-child" + i, parentCategoryId3);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId3 = result.get().getCategoryId();
        }

        Category childCategory1 = categoryRepository.findById(childCategoryId1).get();

        log.info("상품 생성");
        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("create-product-category1-" + i)
                    .productName("product" + i)
                    .companyCode("test-company1")
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory1)
                    .productDiscount(new ProductDiscount())
                    .build();

            productRepository.save(product);
        }

        Category childCategory2 = categoryRepository.findById(childCategoryId2).get();

        log.info("상품 생성");
        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("create-product-category2-" + i)
                    .productName("product" + i)
                    .companyCode("test-company2")
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory2)
                    .productDiscount(new ProductDiscount())
                    .build();

            productRepository.save(product);
        }

        Category childCategory3 = categoryRepository.findById(childCategoryId3).get();

        log.info("상품 생성");
        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("create-product-category3-" + i)
                    .productName("product" + i)
                    .companyCode("test-company3")
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory3)
                    .productDiscount(new ProductDiscount())
                    .build();

            productRepository.save(product);
        }
    }

    @AfterEach
    public void drawLineByTestAfter() {

        log.info("===================================================== END =====================================================");
    }
}
