package com.yosep.product.product.mq.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ProductToCouponProducer {
    private final String topic1 = "exam";

    private final KafkaTemplate<Integer, String> template;

    public ProductToCouponProducer(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    public void sendToCouponTest(String payload) throws ExecutionException, InterruptedException {
        System.out.println(this.template.send(topic1, payload).get());
    }
}
