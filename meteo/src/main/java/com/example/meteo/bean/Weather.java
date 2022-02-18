package com.example.meteo.bean;

public class Weather {
    private String city;
    private String zipCode;
    private String weather;
    private String country;

    public Weather(String city, String zipCode, String weather, String country) {
        this.city = city;
        this.zipCode = zipCode;
        this.weather = weather;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", weather='" + weather + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
