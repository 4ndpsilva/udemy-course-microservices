package com.udemy.customerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CustomerMSApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerMSApplication.class, args);
	}
}