package com.yosep.product.product.service;

import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import com.yosep.product.jpa.common.entity.PageRequest;
import com.yosep.product.jpa.product.data.repository.ProductEventRepository;
import com.yosep.product.jpa.product.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryService {
    private final ProductRepository productRepository;
    private final ProductEventRepository productEventRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void readProductsByCategory(PageRequest pageRequest, String categoryId) {
        
    }
}
