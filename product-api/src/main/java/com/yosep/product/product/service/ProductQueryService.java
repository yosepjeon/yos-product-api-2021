package com.yosep.product.product.service;

import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.entity.PageRequest;
import com.yosep.product.jpa.product.data.dto.SelectedProductDtoV2;
import com.yosep.product.jpa.product.data.entity.Product;
import com.yosep.product.jpa.product.data.repository.ProductEventRepository;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryService {
    private final ProductRepository productRepository;
    private final ProductEventRepository productEventRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void readProductById(String productId) {
//        Optional<Product> optionalProduct = productRepository.findById(productId);

    }

    public Optional<List<SelectedProductDtoV2>> readProductsByCategory(PageRequest pageable, String categoryId) {
        List<SelectedProductDtoV2> result = productRepository.findAllByCategory(pageable.of(), categoryId)
                .orElse(Collections.emptyList());

        result.forEach(p -> {
                    p.add(Link.of("http://localhost:8001/products/"));
                    p.add(Link.of(""));
                });

        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }


}
