package com.yosep.product.product.controller.handler;

import com.yosep.product.product.service.ProductReactiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class ProductCommandHandler {
    private final ProductReactiveService productReactiveService;

    public Mono<ServerResponse> productTest(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.create(objectMonoSink -> objectMonoSink.success("Test 입니다.")), String.class);
    }

    public Mono<ServerResponse> processOrderSaga(ServerRequest serverRequest) {
        serverRequest.bodyToMono(Map.class);

        return ok()
                .build();
    }
}
