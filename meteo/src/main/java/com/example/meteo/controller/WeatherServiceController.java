package com.example.meteo.controller;

import com.example.meteo.bean.Weather;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "WeatherService", description = "Service to retrieve weather")
public class WeatherServiceController {

    @Autowired
    Environment environment;

    public static final List<Weather> weathers = new ArrayList<Weather>() {
        private static final long serialVersionUID = -3970206781360313502L;

        {
            add(new Weather("Toulon", "83000", "Beau", "France"));
            add(new Weather("Toulouse", "31000", "Beau", "France"));
            add(new Weather("Rome", "667", "Pieno di sole", "Italie"));
            add(new Weather("Berlin", "10000", "Nicht verrückt ", "Allemagne"));
        }
    };

    @GetMapping("/backend")
    public String backend() {
        System.out.println("Inside MyRestController::backend...");

        String serverPort = environment.getProperty("local.server.port");

        System.out.println("Port : " + serverPort);

        return "Hello form Backend!!! " + " Host : localhost " + " :: Port : " + serverPort;
    }

    @ApiOperation(value = "Get weathers by city", response = Weather.class, tags = "getWeatherByCity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "city not found") })
    @RequestMapping(
            name = "getWeatherByCity",
            method = RequestMethod.GET,
            value = "/city/{city}"
    )
    public Weather getWeatherByCity(@PathVariable String city){
        try {
            return weathers.stream()
                    .filter(weather -> city.toUpperCase().equals(weather.getCity().toUpperCase()))
                    .findAny()
                    .orElse(null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get weathers by zipcode", response = Weather.class, tags = "getWeatherByZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "zipcode not found") })
    @RequestMapping(
            name = "getWeatherByZipCode",
            method = RequestMethod.GET,
            value = "/zipCode/{zipCode}"
    )
    public Weather getWeatherByZipCode(@PathVariable String zipCode){
        try {
            return weathers.stream()
                    .filter(weather -> zipCode.equals(weather.getZipCode()))
                    .findAny()
                    .orElse(null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get weathers by country", response = Weather.class, responseContainer = "List", tags = "getWeatherByCountry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "country not found") })
    @RequestMapping(
            name = "getWeatherByCountry",
            method = RequestMethod.GET,
            value = "country/{country}"
    )
    public List<Weather> getWeatherByCountry(@PathVariable String country){
        try {
            return weathers.stream()
                    .filter(weather -> country.toUpperCase().equals(weather.getCountry().toUpperCase()))
                    .collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
