package com.example.weather.controller;

import com.example.weather.service.WeatherHourlyService;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherHourlyController {

    @Autowired
    private Logger loggerHourly;

    private final WeatherHourlyService weatherHourlyService;

    public WeatherHourlyController(
            WeatherHourlyService weatherHourlyServiceImpl) {
        weatherHourlyService = weatherHourlyServiceImpl;
    }

    @GetMapping("/today")
    public String getWeatherHourly(Model model) {
        loggerHourly.info("Getting hourly weather");
        model.addAttribute("weatherHourly",
                weatherHourlyService.getWeatherHourly());
        return "today";
    }

}
