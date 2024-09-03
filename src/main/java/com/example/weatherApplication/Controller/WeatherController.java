package com.example.weatherApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherApplication.Model.WeatherData;
import com.example.weatherApplication.Service.WeatherService;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("city/{cityName}")
    public ResponseEntity<WeatherData> getWeather(@PathVariable("cityName") String cityName) {
        WeatherData weatherData = weatherService.getWeatherByCity(cityName);
        if (weatherData != null) {
            return new ResponseEntity<>(weatherData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
