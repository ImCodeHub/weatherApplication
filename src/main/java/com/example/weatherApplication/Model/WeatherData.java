package com.example.weatherApplication.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
    private String weatherDescription;
    private int windSpeed;
    private int pressure;
    private int humidity;
    private int feelslike;
    private int visibility;
    private String isDay;
}
