package com.yosep.product.product.service;

import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.product.data.dto.ProductDtoForCreation;
import com.yosep.product.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /*
    * 상품 생성
    * Logic:
    * 1. dto에서 categoryId를 가져와서 해당 카테고리가 있는지 검사
    * 1-1. 카테고리가 존재한다면 2번으로 진행.
    * 1-2. 카테고리가 존재하지 않는다면 Exception 던지기.
    * 2.
     */
    @Transactional(readOnly = false)
    public void createProduct(ProductDtoForCreation productDtoForCreation) {
//        categoryRepository.findById(productDtoForCreation)
    }
}
