package com.yosep.product.product.service;

import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.exception.NotExistCategoryException;
import com.yosep.product.jpa.common.logic.RandomIdGenerator;
import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.mapper.product.ProductMapper;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Product createProduct(ProductDtoForCreation productDtoForCreation) {
        String productId = RandomIdGenerator.createId();

        Optional<Category> optionalCategory = categoryRepository.findById(productDtoForCreation.getCategory());
        if(optionalCategory.isEmpty()) {
            throw new NotExistCategoryException("해당 카테고리가 존재하지 않습니다.");
        }


        Category selectedCategory = optionalCategory.get();

        while (true) {
            if (productRepository.existsById(productId)) {
                productId = RandomIdGenerator.createId();
                continue;
            } else {
//                Product product = Product.builder()
//                        .productId(productId)
//                        .productName(productDtoForCreation.getProductName())
//                        .productPrice(productDtoForCreation.getProductPrice())
//                        .stockQuantity(productDtoForCreation.getStockQuantity())
//                        .productDetail(productDtoForCreation.getProductDetail())
//                        .category(category)
//                        .build();
                productDtoForCreation.setProductId(productId);
                Product product = ProductMapper.INSTANCE.productDtoForCreationToProduct(productDtoForCreation);
                product.setCategory(selectedCategory);

                Product createdProduct = productRepository.save(product);

                return null;
            }
        }
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
