package com.yosep.product.product.service;

import com.yosep.product.product.repository.ReactiveProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductReactiveService {
    private final ReactiveProductRepository reactiveProductRepository;

    public void findByPage() {
        reactiveProductRepository.findAll();
    }
}
