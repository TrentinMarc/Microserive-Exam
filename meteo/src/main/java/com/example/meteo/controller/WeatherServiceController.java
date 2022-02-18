package com.example.meteo.controller;

import com.example.meteo.bean.Weather;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherServiceController {

    public static final List<Weather> weathers = new ArrayList<Weather>() {
        private static final long serialVersionUID = -3970206781360313502L;

        {
            add(new Weather("Toulon", "83000", "Beau", "France"));
            add(new Weather("Toulouse", "31000", "Beau", "France"));
            add(new Weather("Rome", "667", "Pieno di sole", "Italie"));
            add(new Weather("Berlin", "10000", "Nicht verrückt ", "Allemagne"));
        }
    };

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
