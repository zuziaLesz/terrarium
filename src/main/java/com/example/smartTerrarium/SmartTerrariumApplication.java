package com.example.smartTerrarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmartTerrariumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartTerrariumApplication.class, args);
	}

}
