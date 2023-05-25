package com.example.weather.service;

import com.example.weather.model.WeatherHourly;

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
public class WeatherHourlyServiceImpl implements WeatherHourlyService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<WeatherHourly> getWeatherHourly() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&hourly=temperature_2m,precipitation_probability,precipitation,relativehumidity_2m,windspeed_10m,uv_index&forecast_days=1&timezone=auto";
        String forecast = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(forecast);
        JSONObject hourly = (JSONObject) json.get("hourly");

        List<BigDecimal> precipitationList =
                JSONTools.getList(hourly, "precipitation");
        List<Integer> precipitationProbabilityList =
                JSONTools.getList(hourly, "precipitation_probability");
        List<Integer> relativeHumidity2mList =
                JSONTools.getList(hourly, "relativehumidity_2m");
        List<BigDecimal> temperature2mList =
                JSONTools.getList(hourly, "temperature_2m");
        List<String> timeList = JSONTools.getList(hourly, "time");
        List<BigDecimal> uvIndexList = JSONTools.getList(hourly, "uv_index");
        List<BigDecimal> windSpeed10mList =
                JSONTools.getList(hourly, "windspeed_10m");

        List<WeatherHourly> weatherHourlyList = new ArrayList<>();
        for (int i = 0; i < timeList.size(); ++i) {
            weatherHourlyList.add(new WeatherHourly(
                    precipitationList.get(i),
                    precipitationProbabilityList.get(i),
                    relativeHumidity2mList.get(i),
                    temperature2mList.get(i),
                    timeList.get(i),
                    uvIndexList.get(i),
                    windSpeed10mList.get(i))
            );
        }

        return weatherHourlyList;
    }

}
