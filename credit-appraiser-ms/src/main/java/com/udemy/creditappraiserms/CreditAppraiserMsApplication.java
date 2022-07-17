package com.udemy.creditappraiserms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CreditAppraiserMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CreditAppraiserMsApplication.class, args);
	}
}