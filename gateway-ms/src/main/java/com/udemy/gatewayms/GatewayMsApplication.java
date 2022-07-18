package com.udemy.gatewayms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class GatewayMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayMsApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder
				.routes()
					.route(r -> r.path("/api/customers/**").uri("lb://customer-ms"))
					.route(r -> r.path("/api/cards/**").uri("lb://card-ms"))
					.route(r -> r.path("/api/credit-appraisers/**").uri("lb://credit-appraiser-ms"))
				.build();
	}
}