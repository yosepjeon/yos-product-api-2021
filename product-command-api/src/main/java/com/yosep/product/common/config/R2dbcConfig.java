package com.yosep.product.common.config;

import io.r2dbc.spi.ConnectionFactory;
import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Value("${spring.r2dbc.url}")
    String url = "";
    @Value("${spring.r2dbc.host}")
    String host = "";
    @Value("${spring.r2dbc.port}")
    int port = 0;
    @Value("${spring.r2dbc.database}")
    String database = "";
    @Value("${spring.r2dbc.username}")
    String userName = "";
    @Value("${spring.r2dbc.password}")
    String password = "";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new MariadbConnectionFactory(MariadbConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .database(database)
                .username(userName)
                .password(password)
                .build());
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate() {
        return new R2dbcEntityTemplate(connectionFactory());
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
