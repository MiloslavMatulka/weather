package com.example.weather.config;

import com.example.weather.controller.WeatherDailyController;
import com.example.weather.controller.WeatherHourlyController;
import com.example.weather.controller.WeatherTestController;
import com.example.weather.exception.GlobalExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfig {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

    @Bean
    public Logger loggerDaily() {
        return LoggerFactory.getLogger(WeatherDailyController.class);
    }

    @Bean
    public Logger loggerHourly() {
        return LoggerFactory.getLogger(WeatherHourlyController.class);
    }

    @Bean
    public Logger loggerTest() {
        return LoggerFactory.getLogger(WeatherTestController.class);
    }

    @Bean
    public String nameOfAuthor() {
        return "Miloslav Matulka";
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
