package com.yosep.product.product.mq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yosep.product.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerFromOrder {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = {"revert-product-step"}, groupId = "revert-product-step-0")
    public void processRevertProductEvent() {
        System.out.println("revert-product-step");
    }


}
