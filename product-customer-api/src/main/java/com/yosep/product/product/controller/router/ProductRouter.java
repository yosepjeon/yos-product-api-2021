package com.yosep.product.product.controller.router;

import com.yosep.product.product.controller.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ProductRouter {
    private final ProductHandler productHandler;

    @Bean
    public RouterFunction<?> productFunction() {

        return route()
                .GET("/", accept(MediaType.APPLICATION_JSON), productHandler::productTest)
                .build();
    }
}
