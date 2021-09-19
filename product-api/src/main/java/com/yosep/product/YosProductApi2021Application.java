package com.yosep.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class YosProductApi2021Application {
	public static void main(String[] args) {

		SpringApplication.run(YosProductApi2021Application.class, args);
	}
}
