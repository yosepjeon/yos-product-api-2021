package com.yosep.product.product.controller.router;

import com.yosep.product.product.controller.handler.ProductQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ProductQueryRouter {
    private final ProductQueryHandler productQueryHandler;
    private final String BASE_URL = "/product-query";

    @Bean
    public RouterFunction<?> productTestQueryFunction() {

        return route()
                .GET(BASE_URL + "/test", accept(MediaType.APPLICATION_JSON), productQueryHandler::productTest)
                .build();
    }
}
