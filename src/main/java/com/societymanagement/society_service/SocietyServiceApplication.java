package com.societymanagement.society_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = "com.societymanagement.society_service",
		exclude = {org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class}
)
@EnableJpaRepositories
@EnableFeignClients
public class SocietyServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SocietyServiceApplication.class, args);
	}
}
