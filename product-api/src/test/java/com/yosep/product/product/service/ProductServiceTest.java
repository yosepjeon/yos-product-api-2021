package com.yosep.product.product.service;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.jpa.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.exception.InvalidStockValueException;
import com.yosep.product.jpa.common.exception.NotEqualProductPrice;
import com.yosep.product.jpa.common.exception.NotExistElementException;
import com.yosep.product.jpa.product.data.dto.request.OrderProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.request.ProductStepDtoForCreation;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.entity.ProductDiscount;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import com.yosep.product.jpa.product.data.repository.ProductTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Slf4j
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductServiceTest {
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private String parentCategoryId1;
    private String parentCategoryId2;
    private String parentCategoryId3;
    private String childCategoryId1;
    private String childCategoryId2;
    private String childCategoryId3;

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
                    .companyCode("test-company1")
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
                    .companyCode("test-company1")
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
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        log.info("===================================================== END =====================================================");
    }

    @Autowired
    public ProductServiceTest(CategoryService categoryService, CategoryRepository categoryRepository, ProductTestRepository productTestRepository, ProductRepository productRepository, ProductService productService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Test
    @DisplayName("[Product Service] 상품 생성 테스트")
    public void 상품_생성_테스트() {
        ProductDtoForCreation productDtoForCreation = new ProductDtoForCreation(
                "test1",
                100000,
                100,
                "[Product Service] 상품 생성 테스트",
                "test",
                Collections.emptyList()
        );

//        Product product =  ProductMapper.INSTANCE.productDtoForCreationToProduct(productDtoForCreation);

//        log.info();
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 성공 테스트")
    public void 주문_saga_상품_스텝_성공_테스트() {
        log.info("[Product Service] 주문 saga 상품 스텝 성공 테스트");

        List<OrderProductDtoForCreation> orderProductDtos = new ArrayList<>();

        Map<String, Long> beforeProductQuantities = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            Product selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                    selectedProduct.getProductId(),
                    2,
                    "READY",
                    selectedProduct.getProductPrice()
            );

            beforeProductQuantities.put(selectedProduct.getProductId(), selectedProduct.getStockQuantity());
            orderProductDtos.add(orderProductDtoForCreation);
        }

        ProductStepDtoForCreation productStepDtoForCreation = new ProductStepDtoForCreation(
                "product-saga-order-test",
                0,
                orderProductDtos,
                "READY"
        );

        ProductStepDtoForCreation result = productService.processProductStep(productStepDtoForCreation);
        log.info(result.toString());

        Map<String, Long> afterProductQuantities = new HashMap<>();
        for (OrderProductDtoForCreation orderProductDtoForCreation : orderProductDtos) {
            Product selectedProduct = productRepository.findById(orderProductDtoForCreation.getProductId()).get();

            log.info("\n[처리 전]productId: " + selectedProduct.getProductId() + ", quantity: " + beforeProductQuantities.get(selectedProduct.getProductId())
                    + "\n[처리 후]productId: " + selectedProduct.getProductId() + ", quantity: " + selectedProduct.getStockQuantity());
            Assertions.assertEquals(beforeProductQuantities.get(selectedProduct.getProductId()), selectedProduct.getStockQuantity() + orderProductDtoForCreation.getCount());
        }

        log.info("ProductStepDtoForCreation 상태");
        productStepDtoForCreation.getOrderProductDtos().forEach(element -> {
            log.info("productId= " + element.getProductId() + ", state= " + element.getState());
        });
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 NotExistElementException 테스트")
    public void 주문_saga_상품_스텝_NotExistElementException_테스트() {
        log.info("[Product Service] 주문 saga 상품 스텝 NotExistElementException 테스트");

        List<OrderProductDtoForCreation> orderProductDtos = new ArrayList<>();


        OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                "EMPTY",
                2,
                "READY",
                100000
        );

        orderProductDtos.add(orderProductDtoForCreation);


        ProductStepDtoForCreation productStepDtoForCreation = new ProductStepDtoForCreation(
                "product-saga-order-test",
                0,
                orderProductDtos,
                "READY"
        );

        Assertions.assertThrows(NotExistElementException.class, () -> productService.processProductStep(productStepDtoForCreation));
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 NotEqualProductPrice 테스트")
    public void 주문_saga_상품_스텝_NotEqualProductPrice_테스트() {
        log.info("[Product Service] 주문 saga 상품 스텝 NotEqualProductPrice 테스트");

        List<OrderProductDtoForCreation> orderProductDtos = new ArrayList<>();


        OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                "create-product-category1-1",
                2,
                "READY",
                1000000000
        );

        orderProductDtos.add(orderProductDtoForCreation);


        ProductStepDtoForCreation productStepDtoForCreation = new ProductStepDtoForCreation(
                "product-saga-order-test",
                0,
                orderProductDtos,
                "READY"
        );

        Assertions.assertThrows(NotEqualProductPrice.class, () -> productService.processProductStep(productStepDtoForCreation));
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 NotExistElementException 롤백 테스트")
    public void 주문_saga_상품_스텝_NotExistElementException_롤백_테스트() {
        Product selectedProduct;

        log.info("[Product Service] 주문 saga 상품 스텝 NotExistElementException 롤백 테스트");

        List<OrderProductDtoForCreation> orderProductDtos = new ArrayList<>();

        log.info("<연산 전 리스트>");
        for(int i=0;i<5;i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            log.info("productId= " + selectedProduct.getProductId() + ", stockQuantity= " + selectedProduct.getStockQuantity());
        }

        Map<String, Long> beforeProductQuantities = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                    selectedProduct.getProductId(),
                    1,
                    "READY",
                    selectedProduct.getProductPrice()
            );

            beforeProductQuantities.put(selectedProduct.getProductId(), selectedProduct.getStockQuantity());
            orderProductDtos.add(orderProductDtoForCreation);
        }

        OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                "EMPTY",
                1,
                "READY",
                1000000
        );

        orderProductDtos.add(orderProductDtoForCreation);

        log.info("saga process Exception 트랜잭션 발생시키기");

        ProductStepDtoForCreation productStepDtoForCreation = new ProductStepDtoForCreation(
                "product-saga-order-test",
                0,
                orderProductDtos,
                "READY"
        );


        Assertions.assertThrows(NotExistElementException.class, () -> productService.processProductStep(productStepDtoForCreation));

        log.info("<연산 후 리스트>");
        for(int i=0;i<5;i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            log.info("productId= " + selectedProduct.getProductId() + ", stockQuantity= " + selectedProduct.getStockQuantity());
        }

        log.info("ProductStepDtoForCreation 상태");
        productStepDtoForCreation.getOrderProductDtos().forEach(element -> {
            log.info("productId= " + element.getProductId() + ", state= " + element.getState());
        });
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 NotEqualProductPrice 롤백 테스트")
    public void 주문_saga_상품_스텝_NotEqualProductPrice_롤백_테스트() {
        Product selectedProduct;

        log.info("[Product Service] 주문 saga 상품 스텝 NotEqualProductPrice 롤백 테스트");

        List<OrderProductDtoForCreation> orderProductDtos = new ArrayList<>();

        log.info("<연산 전 리스트>");
        for(int i=0;i<5;i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            log.info("productId= " + selectedProduct.getProductId() + ", stockQuantity= " + selectedProduct.getStockQuantity());
        }

        Map<String, Long> beforeProductQuantities = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                    selectedProduct.getProductId(),
                    1,
                    "READY",
                    selectedProduct.getProductPrice()
            );

            beforeProductQuantities.put(selectedProduct.getProductId(), selectedProduct.getStockQuantity());
            orderProductDtos.add(orderProductDtoForCreation);
        }

        OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                "create-product-category1-4",
                1,
                "READY",
                1000000
        );

        orderProductDtos.add(orderProductDtoForCreation);

        log.info("saga process Exception 트랜잭션 발생시키기");

        ProductStepDtoForCreation productStepDtoForCreation = new ProductStepDtoForCreation(
                "product-saga-order-test",
                0,
                orderProductDtos,
                "READY"
        );

        Assertions.assertThrows(NotEqualProductPrice.class, () -> productService.processProductStep(productStepDtoForCreation));

        log.info("<연산 후 리스트>");
        for(int i=0;i<5;i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            log.info("productId= " + selectedProduct.getProductId() + ", stockQuantity= " + selectedProduct.getStockQuantity());
        }

        log.info("ProductStepDtoForCreation 상태");
        productStepDtoForCreation.getOrderProductDtos().forEach(element -> {
            log.info("productId= " + element.getProductId() + ", state= " + element.getState());
        });
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 NotEqualProductPrice 롤백 테스트")
    public void 주문_saga_상품_스텝_InvalidStockValueException_롤백_테스트() {
        Product selectedProduct;

        log.info("[Product Service] 주문 saga 상품 스텝 NotEqualProductPrice 롤백 테스트");

        List<OrderProductDtoForCreation> orderProductDtos = new ArrayList<>();

        log.info("<연산 전 리스트>");
        for(int i=0;i<5;i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            log.info("productId= " + selectedProduct.getProductId() + ", stockQuantity= " + selectedProduct.getStockQuantity());
        }

        Map<String, Long> beforeProductQuantities = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                    selectedProduct.getProductId(),
                    1,
                    "READY",
                    selectedProduct.getProductPrice()
            );

            beforeProductQuantities.put(selectedProduct.getProductId(), selectedProduct.getStockQuantity());
            orderProductDtos.add(orderProductDtoForCreation);
        }

        Product product = productRepository.findById("create-product-category1-4").get();
        OrderProductDtoForCreation orderProductDtoForCreation = new OrderProductDtoForCreation(
                product.getProductId(),
                100000,
                "READY",
                product.getProductPrice()
        );

        orderProductDtos.add(orderProductDtoForCreation);

        log.info("saga process Exception 트랜잭션 발생시키기");

        ProductStepDtoForCreation productStepDtoForCreation = new ProductStepDtoForCreation(
                "product-saga-order-test",
                0,
                orderProductDtos,
                "READY"
        );

        Assertions.assertThrows(InvalidStockValueException.class, () -> productService.processProductStep(productStepDtoForCreation));

        log.info("<연산 후 리스트>");
        for(int i=0;i<5;i++) {
            selectedProduct = productRepository.findById("create-product-category1-" + i).get();

            log.info("productId= " + selectedProduct.getProductId() + ", stockQuantity= " + selectedProduct.getStockQuantity());
        }

        log.info("ProductStepDtoForCreation 상태");
        productStepDtoForCreation.getOrderProductDtos().forEach(element -> {
            log.info("productId= " + element.getProductId() + ", state= " + element.getState());
        });
    }

    @Test
    @DisplayName("[Product Entity] 상품 도메인 삭제 테스트")
    public void 상품_도메인_삭제_테스트() {
        productRepository.findById("");
    }
}
