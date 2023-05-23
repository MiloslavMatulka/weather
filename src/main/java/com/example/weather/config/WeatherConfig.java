package com.example.weather.config;

import com.example.weather.controller.WeatherHourlyController;

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
        return LoggerFactory.getLogger(WeatherHourlyController.class);
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
