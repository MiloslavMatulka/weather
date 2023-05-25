package com.example.weather.service;

import com.example.weather.model.WeatherHourly;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherHourlyService {

    List<WeatherHourly> getWeatherHourly();

}
