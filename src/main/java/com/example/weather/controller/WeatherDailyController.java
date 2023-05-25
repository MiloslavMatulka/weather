package com.example.weather.controller;

import com.example.weather.service.WeatherDailyService;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherDailyController {

    @Autowired
    private Logger loggerDaily;

    private final WeatherDailyService weatherDailyService;

    public WeatherDailyController(
            WeatherDailyService weatherDailyServiceImpl) {
        weatherDailyService = weatherDailyServiceImpl;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        loggerDaily.info("Viewing home page, getting daily weather");
        model.addAttribute("weatherDaily",
                weatherDailyService.getWeatherDaily());
        return "index";
    }

}
