package com.example.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDaily {

    private Integer precipitationProbabilityMean;
    private BigDecimal precipitationSum;
    private String sunrise;
    private String sunset;
    private BigDecimal temperature2mMax;
    private BigDecimal temperature2mMin;
    private String time;
    private BigDecimal uvIndexMax;
    private BigDecimal windSpeed10mMax;
}
