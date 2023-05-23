package com.example.weather.service;

import com.example.weather.model.WeatherDaily;

import lombok.AllArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WeatherDailyServiceImpl implements WeatherDailyService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<WeatherDaily> getWeatherDaily() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_mean,precipitation_sum,windspeed_10m_max,sunrise,sunset,uv_index_max&forecast_days=16&timezone=auto";
        String forecast = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(forecast);
        JSONObject daily = (JSONObject) json.get("daily");

        // Precipitation probability mean units: %
        JSONArray precipitationProbabilityMean =
                (JSONArray) daily.get("precipitation_probability_mean");
        List<Integer> precipitationProbabilityMeanList =
                JsonTools.convertToList(precipitationProbabilityMean);

        // Precipitation units: mm
        JSONArray precipitationSum =
                (JSONArray) daily.get("precipitation_sum");
        List<BigDecimal> precipitationSumList =
                JsonTools.convertToList(precipitationSum);

        // Sunrise units: iso8601
        JSONArray sunrise =
                (JSONArray) daily.get("sunrise");
        List<String> sunriseList = JsonTools.convertToList(sunrise);

        // Sunset units: iso8601
        JSONArray sunset =
                (JSONArray) daily.get("sunset");
        List<String> sunsetList = JsonTools.convertToList(sunset);

        // Temperature units: °C
        JSONArray temperature2mMax =
                (JSONArray) daily.get("temperature_2m_max");
        List<BigDecimal> temperature2mMaxList =
                JsonTools.convertToList(temperature2mMax);

        // Temperature units: °C
        JSONArray temperature2mMin =
                (JSONArray) daily.get("temperature_2m_min");
        List<BigDecimal> temperature2mMinList =
                JsonTools.convertToList(temperature2mMin);

        // Time units: iso8601
        JSONArray time = (JSONArray) daily.get("time");
        List<String> timeList = JsonTools.convertToList(time);

        // UV index units: none
        JSONArray uvIndexMax =
                (JSONArray) daily.get("uv_index_max");
        List<BigDecimal> uvIndexMaxList = JsonTools.convertToList(uvIndexMax);

        // Wind speed units: km/h
        JSONArray windSpeed10mMax =
                (JSONArray) daily.get("windspeed_10m_max");
        List<BigDecimal> windSpeed10mMaxList =
                JsonTools.convertToList(windSpeed10mMax);

        List<WeatherDaily> weatherDailyList = new ArrayList<>();
        for (int i = 0; i < time.length(); ++i) {
            weatherDailyList.add(new WeatherDaily(
                    precipitationProbabilityMeanList.get(i),
                    precipitationSumList.get(i),
                    sunriseList.get(i),
                    sunsetList.get(i),
                    temperature2mMaxList.get(i),
                    temperature2mMinList.get(i),
                    timeList.get(i),
                    uvIndexMaxList.get(i),
                    windSpeed10mMaxList.get(i))
            );
        }

        return weatherDailyList;
    }
}
