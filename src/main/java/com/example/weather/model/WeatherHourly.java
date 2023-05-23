package com.example.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherHourly {

    private BigDecimal precipitation;
    private Integer precipitationProbability;
    private Integer relativeHumidity2m;
    private BigDecimal temperature2m;
    private String time;
    private BigDecimal uvIndex;
    private BigDecimal windSpeed10m;
}
