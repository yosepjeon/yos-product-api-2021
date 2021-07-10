package com.yosep.product.product.controller.router;

import com.yosep.product.product.controller.handler.ProductCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
@RequiredArgsConstructor
public class ProductCommandRouter {
    private final ProductCommandHandler productCommandHandler;
    private final String BASE_URL = "/product";

    @Bean
    public RouterFunction<?> productFunction() {
        return null;
    }
}
