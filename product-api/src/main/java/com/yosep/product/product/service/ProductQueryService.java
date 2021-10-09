package com.yosep.product.product.service;

import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.entity.PageRequest;
import com.yosep.product.jpa.product.data.dto.SelectedProductDtoV2;
import com.yosep.product.jpa.product.data.repository.ProductEventRepository;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryService {
    private final ProductRepository productRepository;
    private final ProductEventRepository productEventRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void readProductById(String productId) {
//        productRepository.findById()
    }

    public void readProductsByCategory(PageRequest pageable, String categoryId) {
        productRepository.findAllByCategory(pageable.of(), categoryId)
                .orElse(Collections.emptyList())
                .forEach(p -> {
                    p.add(Link.of("http://localhost:8001/products/"));
                    p.add(Link.of(""));
                });


    }
}
