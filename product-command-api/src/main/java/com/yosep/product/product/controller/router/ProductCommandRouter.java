package com.yosep.product.product.controller.router;

import com.yosep.product.product.controller.handler.ProductCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ProductCommandRouter {
    private final ProductCommandHandler productCommandHandler;
    private final String BASE_URL = "/product-command";

    @Bean
    public RouterFunction<?> productTestCommandFunction() {
        return route()
                .POST(BASE_URL + "/test", accept(MediaType.APPLICATION_JSON), productCommandHandler::productTest)
                .build();
    }
}
