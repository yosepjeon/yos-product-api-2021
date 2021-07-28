package com.yosep.product.product.service;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseProductIntegrationTest;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.exception.NotExistElementException;
import com.yosep.product.jpa.product.data.dto.request.OrderProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.request.ProductStepDtoForCreation;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import com.yosep.product.jpa.product.data.repository.ProductTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductServiceTest extends BaseProductIntegrationTest {
    private final ProductService productService;

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
                0,
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
                0,
                orderProductDtos
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
                0,
                orderProductDtos
        );

        Assertions.assertThrows(NotExistElementException.class, ()->productService.processProductStep(productStepDtoForCreation));
    }

    @Test
    @DisplayName("[Product Service] 주문 saga 상품 스텝 NotExistElementException 테스트")
    public void 주문_saga_상품_스텝_NotEqualProductPrice_테스트() {

    }

    @Test
    @DisplayName("[Product Entity] 상품 도메인 삭제 테스트")
    public void 상품_도메인_삭제_테스트() {
        productRepository.findById("");
    }
}
