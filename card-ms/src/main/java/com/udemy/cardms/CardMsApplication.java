package com.udemy.cardms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CardMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CardMsApplication.class, args);
	}
}