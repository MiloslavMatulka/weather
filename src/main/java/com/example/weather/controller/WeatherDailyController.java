package com.example.weather.controller;

import com.example.weather.service.WeatherDailyService;

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
public class WeatherDailyController {

    @Autowired
    private Logger logger;

    @Autowired
    private String nameOfAuthor;
    private final WeatherDailyService weatherDailyService;

    public WeatherDailyController(
            WeatherDailyService weatherDailyServiceImpl) {
        weatherDailyService = weatherDailyServiceImpl;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("weatherDaily",
                weatherDailyService.getWeatherDaily());
        logger.info("Viewing home page");
        return "index";
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception e) {
        return new ErrorResponse(nameOfAuthor, e.getLocalizedMessage(),
                e.getStackTrace(), LocalDateTime.now());
    }
}
