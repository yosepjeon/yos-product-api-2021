package com.yosep.product.product.service;

import com.yosep.product.product.repository.ReactiveProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductReactiveService {
    private final ReactiveProductRepository reactiveProductRepository;

    public Mono<String> test(ReceiverRecord<String, String> receiverRecordFlux) {
        return Mono.create(stringMonoSink -> {
                System.out.println("####");
                stringMonoSink.success("test");
        });
    }

    public void findByPage() {
//        reactiveProductRepository
        reactiveProductRepository.findAll();
    }

    public void updateProductForSaga() {

    }
}
