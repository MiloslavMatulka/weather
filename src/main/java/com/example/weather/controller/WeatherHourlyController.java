package com.example.weather.controller;

import com.example.weather.service.WeatherHourlyService;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Controller
public class WeatherHourlyController {

    @Autowired
    private Logger logger;

    @Autowired
    private String nameOfAuthor;
    private final WeatherHourlyService weatherHourlyService;

    public WeatherHourlyController(
            WeatherHourlyService weatherHourlyServiceImpl) {
        weatherHourlyService = weatherHourlyServiceImpl;
    }

    @GetMapping("/hourly")
    public String getWeatherHourly(Model model) {
        model.addAttribute("weatherHourly",
                weatherHourlyService.getWeatherHourly());
        logger.info("Viewing weather hourly");
        return "hourly";
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception e) {
        return new ErrorResponse(nameOfAuthor, e.getLocalizedMessage(),
                e.getStackTrace(), LocalDateTime.now());
    }
}
