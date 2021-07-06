package com.yosep.product.product.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.function.Function;

@Slf4j
public class CustomSubscriber extends BaseSubscriber<ReceiverRecord<String, String>> {
    private Function<ReceiverRecord<String, String>, Mono<Boolean>> runner;
    private Mono<String> value;

    public CustomSubscriber(Function<ReceiverRecord<String, String>, Mono<Boolean>> runner) {
        this.runner = runner;
    }

    public CustomSubscriber(Mono<String> value) {
        this.value = value;
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        request(100);
    }

    @Override
    protected void hookOnNext(ReceiverRecord<String, String> record) {
//        Mono.just(record)
//                .flatMap(runner)
//                .subscribe(r -> {
//                    log.info("[COMMIT] Consume END - {}", record.value());
//                    record.receiverOffset().acknowledge();
//                    request(1);
//                });

        Mono.create(monoSink -> {
            monoSink.success(record);
        })
        .subscribe(r -> {
            log.info("[COMMIT] Consume END - {}", record.value());
            record.receiverOffset().acknowledge();
            request(1);
        });
    }
}
