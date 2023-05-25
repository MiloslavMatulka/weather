package com.example.weather.service;

import com.example.weather.model.WeatherDaily;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherDailyService {

    List<WeatherDaily> getWeatherDaily();

}
