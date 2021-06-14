package com.yosep.product.product.service;

import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.common.logic.RandomIdGenerator;
import com.yosep.product.product.data.dto.ProductDtoForCreation;
import com.yosep.product.product.data.entity.Product;
import com.yosep.product.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /*
    * 상품 생성
    * Logic:
    * 1. productId 생성하여 해당 ID의 상품이 있는지 검사
    * 1-1. 상품 존재한다면 1번 반복.
    * 1-2. 상품이 존재하지 않는다면 2번으로 이동.
    * 2. 상품 생성 및 생성된 상품 엔티티 반환
     */
    @Transactional(readOnly = false)
    public Product createProduct(ProductDtoForCreation productDtoForCreation) {
        String productId = RandomIdGenerator.createId();
        categoryRepository.findById(productDtoForCreation.getCategory());

        while (true) {
            if(productRepository.existsById(productId)) {
                productId = RandomIdGenerator.createId();
                continue;
            }else {
                Product product = Product.builder()
                        .productId(productId)
                        .productName(productDtoForCreation.getProductName())
                        .productPrice(productDtoForCreation.getProductPrice())
                        .stockQuantity(productDtoForCreation.getStockQuantity())
                        .productDetail(productDtoForCreation.getProductDetail())
                        .category(categoryRepository.findById(productDtoForCreation.getCategory()).get())
                        .build();

                return productRepository.save(product);
            }
        }
    }


}
