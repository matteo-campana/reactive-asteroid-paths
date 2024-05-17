package com.example.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AsteroidsPathApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsteroidsPathApplication.class, args);
	}

}
