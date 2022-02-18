package com.example.showWeather.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @Autowired
    RestTemplate restTemplate;

    public String  fallbackMethod(String parameter){
        return "Fallback response:: No weather details available temporarily. Service is probably down...";
    }

    @RequestMapping("/client/frontend")
    public String hi() {
        String randomString = this.restTemplate.getForObject("http://meteo-service/backend", String.class);
        return "Server Response :: " + randomString;
    }

    @RequestMapping(value = "weatherDetails/city/{city}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeatherByCity(@PathVariable String city)
    {
        System.out.println("Getting Weather details for city : " + city);

        String response = restTemplate.exchange("http://meteo-service/city/{city}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, city).getBody();

        System.out.println("Response Body " + response);

        return "Weather city -  " + city + " [ Weather Details " + response+" ]";
    }

    @RequestMapping(value = "weatherDetails/zipCode/{zipCode}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeatherByZipCode(@PathVariable String zipCode)
    {
        System.out.println("Getting Weather details for zipCode : " + zipCode);

        String response = restTemplate.exchange("http://meteo-service/zipCode/{zipCode}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, zipCode).getBody();

        System.out.println("Response Body " + response);

        return "Weather zipCode -  " + zipCode + " [ Weather Details " + response+" ]";
    }

    @RequestMapping(value = "weatherDetails/country/{country}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeatherByCountry(@PathVariable String country)
    {
        System.out.println("Getting Weather details for country : " + country);

        String response = restTemplate.exchange("http://meteo-service/country/{country}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, country).getBody();

        System.out.println("Response Body " + response);

        return "Weather country -  " + country + " [ Weather Details " + response+" ]";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }}
