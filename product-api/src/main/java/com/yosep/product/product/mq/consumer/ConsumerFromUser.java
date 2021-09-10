package com.yosep.product.product.mq.consumer;

import com.yosep.product.cart.service.CartCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerFromUser {
    private final CartCommandService cartCommandService;

    @KafkaListener(topics = {"create-cart"}, groupId = "create-cart-0")
    public void processCreateCartEvent(String userId) {
//        System.out.println("cart 생성 이벤트 발생");
        cartCommandService.createCart(userId);
    }

    @KafkaListener(topics = {"delete-cart"}, groupId = "delete-cart-0")
    public void processDeleteCartEvent(String userId) {
        cartCommandService.deleteCart(userId);
    }
}
