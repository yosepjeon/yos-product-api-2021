package com.yosep.product.product.mq;

import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

public class ProductToCouponSender {
    private final String topic1 = "exam";

    private final KafkaTemplate<Integer, String> template;

    public ProductToCouponSender(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    public void sendToCouponTest(String payload) throws ExecutionException, InterruptedException {
        System.out.println(this.template.send(topic1, payload).get());
    }
}
