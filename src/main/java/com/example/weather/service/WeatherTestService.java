package com.example.weather.service;

import org.springframework.stereotype.Service;

@Service
public interface WeatherTestService {

    String getWeatherDaily();
    String getWeatherDailyForDay(int day);
    String getWeatherHourly();
    String getWeatherHourlyForHour(int hour);

}
