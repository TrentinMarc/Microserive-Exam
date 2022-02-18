package com.example.meteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MeteoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteoApplication.class, args);
	}

}
