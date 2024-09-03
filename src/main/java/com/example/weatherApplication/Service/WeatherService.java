package com.example.weatherApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.weatherApplication.ApiResponse.WeatherResponse;
import com.example.weatherApplication.Model.WeatherData;

@Service
public class WeatherService {
    private final static String apiKey = "f55a71de9b8666d36af5bbb5b5b7f496";

    private final static String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    // using RestTemplate for browsing the API url by the spring boot.
    @Autowired
    private RestTemplate restTemplate;

    public WeatherData getWeatherByCity(String city) {
        String finalApi = API.replace("CITY", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null,WeatherResponse.class);
        WeatherResponse weatherResponse = response.getBody();
        
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            WeatherData weatherData = new WeatherData();
            weatherData.setWeatherDescription(weatherResponse.getCurrent().getWeatherDescriptions().get(0));
            weatherData.setWindSpeed(weatherResponse.getCurrent().getWindSpeed());
            weatherData.setPressure(weatherResponse.getCurrent().getPressure());
            weatherData.setHumidity(weatherResponse.getCurrent().getHumidity());
            weatherData.setFeelslike(weatherResponse.getCurrent().getFeelslike());
            weatherData.setVisibility(weatherResponse.getCurrent().getVisibility());
            weatherData.setIsDay(weatherResponse.getCurrent().getIsDay());

            return weatherData;
        }
        return null;
    }
}
