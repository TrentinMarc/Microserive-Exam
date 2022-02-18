package com.example.meteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "show-weather-service", configuration = RibbonConfiguration.class)
public class MeteoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteoApplication.class, args);
	}

}
