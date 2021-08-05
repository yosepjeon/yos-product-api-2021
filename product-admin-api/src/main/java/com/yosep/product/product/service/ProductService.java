package com.yosep.product.product.service;

import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.exception.InvalidStockValueException;
import com.yosep.product.jpa.common.exception.NotEqualProductPrice;
import com.yosep.product.jpa.common.exception.NotExistCategoryException;
import com.yosep.product.jpa.common.exception.NotExistElementException;
import com.yosep.product.jpa.common.logic.RandomIdGenerator;
import com.yosep.product.jpa.product.data.dto.request.OrderProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.request.ProductStepDtoForCreation;
import com.yosep.product.jpa.product.data.dto.response.CreatedProductDto;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.mapper.product.ProductMapper;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void getProduct() {

    }

    public void getProductListByPage() {

    }

    /*
     * 상품 생성
     * Logic:
     * 1. productId를 생성하여 해당 ID의 상품이 있는지 검사
     * 1-1. 상품 존재한다면 1번 반복.
     * 1-2. 상품이 존재하지 않는다면 2번으로 이동.
     * 2. 상품 생성 및 생성된 상품 엔티티 반환
     */
    @Transactional(readOnly = false)
    public CreatedProductDto createProduct(ProductDtoForCreation productDtoForCreation) {
        String productId = RandomIdGenerator.createId();

        Optional<Category> optionalCategory = categoryRepository.findById(productDtoForCreation.getCategory());
        if (optionalCategory.isEmpty()) {
            throw new NotExistCategoryException("해당 카테고리가 존재하지 않습니다.");
        }


        Category selectedCategory = optionalCategory.get();

        while (true) {
            if (productRepository.existsById(productId)) {
                productId = RandomIdGenerator.createId();
                continue;
            } else {
                productDtoForCreation.setProductId(productId);
                Product product = ProductMapper.INSTANCE.productDtoForCreationToProduct(productDtoForCreation);
                product.setCategory(selectedCategory);

                Product createdProduct = productRepository.save(product);

                CreatedProductDto createdProductDto = ProductMapper.INSTANCE.productToCreatedProductDto(createdProduct);

                return createdProductDto;
            }
        }
    }

    /*
     * SAGA 상품 스텝 진행
     * Logic:
     * 1. DTO로부터 주문 상품들을 가져온다.
     * 2. 주문 상품DTO를 가져온다.
     * 3. 주문 상품의 상태를 PENDING(진행중)으로 바꾼다.
     * 4. 해당 ID의 주문 상품 엔티티를 가져온다.
     * 4-1. 해당 주문 상품이 존재하지 않는다면 RuntimeException 발생.
     * 5. 요청 가격과 실제 가격 일치 여부를 검증한다. 검증 실패시 RuntimeException 발생.
     * 6. 상품 재고를 변환한다.(재고 검증 실패시 RuntimeException 발생.)
     * ** 각각의 Exception 발생시 해당 Exception이름으로 상태 변환
     */
    @Transactional(
            readOnly = false,
            rollbackFor = {NotExistElementException.class, RuntimeException.class, NotEqualProductPrice.class, InvalidStockValueException.class},
            propagation = Propagation.REQUIRED
//            propagation = Propagation.REQUIRES_NEW
    )
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    public ProductStepDtoForCreation processProductStep(ProductStepDtoForCreation productStepDtoForCreation) {
        List<OrderProductDtoForCreation> orderProductDtos = productStepDtoForCreation.getOrderProductDtos();

        for (OrderProductDtoForCreation orderProductDtoForCreation : orderProductDtos) {
            orderProductDtoForCreation.setState("PENDING");
            Optional<Product> optionalSelectedProduct = productRepository.findById(orderProductDtoForCreation.getProductId());

            if (optionalSelectedProduct.isEmpty()) {
                orderProductDtoForCreation.setState("NotExistElementException");
                throw new NotExistElementException(orderProductDtoForCreation.getProductId() + " 해당 상품이 존재하지 않습니다.");
            }

            Product selectedProduct = optionalSelectedProduct.get();

            selectedProduct.validatePrice(orderProductDtoForCreation);

            selectedProduct.decreaseStock(orderProductDtoForCreation);

            orderProductDtoForCreation.setState("COMP");
        }

        return productStepDtoForCreation;
    }

    /*
     * SAGA 상품 스텝 Revert
     */
    @Transactional(
            readOnly = false,
            rollbackFor = {NotExistElementException.class, RuntimeException.class, NotEqualProductPrice.class, InvalidStockValueException.class},
            propagation = Propagation.REQUIRED
//            propagation = Propagation.REQUIRES_NEW
    )
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    public void revertProductStep(ProductStepDtoForCreation productStepDtoForCreation) {

    }

    @Transactional(readOnly = false)
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    public void increaseProductStock() {

    }

    /*
     * 상품 삭제
     * Logic:
     * 1. 해당 아이디의 상품을 삭제.
     */
    @Transactional(readOnly = false)
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    /*
     * 여러 상품 삭제
     * Logic:
     * 1. 리스트를 순회하면서 상품 삭제
     */
    @Transactional(readOnly = false)
    public void deleteProducts(List<String> productIds) {
        for (String productId : productIds) {
            productRepository.deleteById(productId);
        }
    }
}
