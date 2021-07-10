package com.yosep.product.product.controller.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    public Mono<ServerResponse> productTest(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.create(objectMonoSink -> objectMonoSink.success("Test 입니다.")), String.class);
    }

    public Mono<ServerResponse> processOrderSaga(ServerRequest serverRequest) {
        return ok()
                .build();
//                .contentType(MediaType.APPLICATION_JSON)
//                .body();
    }

}
