package com.yosep.product.product.controller.router;

import com.yosep.product.product.controller.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.integration.IntegrationPatternType.router;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ProductRouter {
    private final ProductHandler productHandler;
    private final String BASE_URL = "/product";

    @Bean
    public RouterFunction<?> productFunction() {

        return route()
                .GET(BASE_URL + "/test", accept(MediaType.APPLICATION_JSON), productHandler::productTest)
                .build();
    }
}
