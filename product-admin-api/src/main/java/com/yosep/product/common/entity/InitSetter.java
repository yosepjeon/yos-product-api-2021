package com.yosep.product.common.entity;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.jpa.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.entity.ProductDiscount;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import com.yosep.product.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitSetter implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    private String parentCategoryId1;
    private String parentCategoryId2;
    private String parentCategoryId3;
    private String childCategoryId1;
    private String childCategoryId2;
    private String childCategoryId3;

    @Override
    public void run(String... args) throws NoSuchAlgorithmException {
        deleteTestData();
        setTestData();
    }

    private void setTestData() throws NoSuchAlgorithmException {

        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");

        Category category1 = new Category();
        category1.setCategoryId("test-category-parent1");
        category1.setName("test-category-parent1");

        Category createdCategory1 = categoryRepository.save(category1);
        parentCategoryId1 = createdCategory1.getCategoryId();

        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("test-category1-child" + i, parentCategoryId1);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId1 = result.get().getCategoryId();
        }

        Category category2 = new Category();
        category2.setCategoryId("test-category-parent2");
        category2.setName("test-category-parent2");

        Category createdCategory2 = categoryRepository.save(category2);
        parentCategoryId2 = createdCategory2.getCategoryId();

        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("test-category2-child" + i, parentCategoryId2);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId2 = result.get().getCategoryId();
        }

        Category category3 = new Category();
        category3.setCategoryId("test-category-parent3");
        category3.setName("test-category-parent3");

        Category createdCategory3 = categoryRepository.save(category3);
        parentCategoryId3 = createdCategory3.getCategoryId();

        for (int i = 0; i < 5; i++) {
            CategoryDtoForCreation categoryDtoForCreation = new CategoryDtoForCreation("test-category3-child" + i, parentCategoryId3);
            Optional<Category> result = categoryService.createCategory(categoryDtoForCreation);
            childCategoryId3 = result.get().getCategoryId();
        }

        Category childCategory1 = categoryRepository.findById(childCategoryId1).get();

        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("test-product-category1-" + i)
                    .productName("product" + i)
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory1)
                    .productDiscount(new ProductDiscount())
                    .build();

            productRepository.save(product);
        }

        Category childCategory2 = categoryRepository.findById(childCategoryId2).get();

        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("test-product-category2-" + i)
                    .productName("product" + i)
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory2)
                    .productDiscount(new ProductDiscount())
                    .build();

            productRepository.save(product);
        }

        Category childCategory3 = categoryRepository.findById(childCategoryId3).get();

        for (int i = 0; i < 5; i++) {
            Product product = Product.builder()
                    .productId("test-product-category3-" + i)
                    .productName("product" + i)
                    .productPrice((int) (rand.nextDouble() * 100000))
                    .stockQuantity((int) (rand.nextDouble() * 100))
                    .productDetail("")
                    .category(childCategory3)
                    .productDiscount(new ProductDiscount())
                    .build();

            productRepository.save(product);
        }
    }

    private void deleteTestData() {
        for (int i = 0; i < 5; i++) {
            productService.deleteProduct("test-product-category1-" + i);
            productService.deleteProduct("test-product-category2-" + i);
            productService.deleteProduct("test-product-category3-" + i);
        }

        categoryService.deleteCategoryById("test-category-parent1");

        for (int i = 0; i < 5; i++) {
            categoryService.deleteCategoryById("test-category1-child" + i);
        }

        categoryService.deleteCategoryById("test-category-parent2");

        for (int i = 0; i < 5; i++) {
            categoryService.deleteCategoryById("test-category2-child" + i);
        }

        categoryService.deleteCategoryById("test-category-parent3");

        for (int i = 0; i < 5; i++) {
            categoryService.deleteCategoryById("test-category3-child" + i);
        }
    }
}
