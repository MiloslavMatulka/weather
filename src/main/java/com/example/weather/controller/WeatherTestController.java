package com.example.weather.controller;

import com.example.weather.service.WeatherTestService;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class WeatherTestController {

    @Autowired
    private Logger loggerTest;

    private final WeatherTestService weatherTestService;

    public WeatherTestController(WeatherTestService weatherTestServiceImpl) {
        weatherTestService = weatherTestServiceImpl;
    }

    @GetMapping("/daily")
    public String getWeatherDaily() {
        loggerTest.info("Getting daily weather");
        return weatherTestService.getWeatherDaily();
    }

    @GetMapping("/daily/{day}")
    public String getWeatherDailyForDay(@PathVariable int day) {
        loggerTest.info("Getting daily weather for day " + day);
        return weatherTestService.getWeatherDailyForDay(day);
    }

    @GetMapping("/hourly")
    public String getWeatherHourly() {
        loggerTest.info("Getting hourly weather");
        return weatherTestService.getWeatherHourly();
    }

    @GetMapping("/hourly/{hour}")
    public String getWeatherHourlyForHour(@PathVariable int hour) {
        loggerTest.info("Getting hourly weather for hour " + hour);
        return weatherTestService.getWeatherHourlyForHour(hour);
    }

}
