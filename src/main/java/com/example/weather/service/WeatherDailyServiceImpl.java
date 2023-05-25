package com.example.weather.service;

import com.example.weather.model.WeatherDaily;

import lombok.AllArgsConstructor;

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

        List<Integer> precipitationProbabilityMeanList =
                JSONTools.getList(daily, "precipitation_probability_mean");
        List<BigDecimal> precipitationSumList =
                JSONTools.getList(daily, "precipitation_sum");
        List<String> sunriseList = JSONTools.getList(daily, "sunrise");
        List<String> sunsetList = JSONTools.getList(daily, "sunset");
        List<BigDecimal> temperature2mMaxList =
                JSONTools.getList(daily, "temperature_2m_max");
        List<BigDecimal> temperature2mMinList =
                JSONTools.getList(daily, "temperature_2m_min");
        List<String> timeList = JSONTools.getList(daily, "time");
        List<BigDecimal> uvIndexMaxList =
                JSONTools.getList(daily, "uv_index_max");
        List<BigDecimal> windSpeed10mMaxList =
                JSONTools.getList(daily, "windspeed_10m_max");

        List<WeatherDaily> weatherDailyList = new ArrayList<>();
        for (int i = 0; i < timeList.size(); ++i) {
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
