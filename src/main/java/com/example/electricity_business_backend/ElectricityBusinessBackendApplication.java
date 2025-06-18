package com.example.electricity_business_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ElectricityBusinessBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricityBusinessBackendApplication.class, args);
	}


/*	@GetMapping("/")
	public String home() {
		return "Hello World!";
	}*/
}
