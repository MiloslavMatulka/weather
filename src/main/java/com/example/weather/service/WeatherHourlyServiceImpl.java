package com.example.weather.service;

import com.example.weather.model.WeatherHourly;

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
public class WeatherHourlyServiceImpl implements WeatherHourlyService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<WeatherHourly> getWeatherHourly() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&hourly=temperature_2m,precipitation_probability,precipitation,relativehumidity_2m,windspeed_10m,uv_index&forecast_days=1&timezone=auto";
        String forecast = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(forecast);
        JSONObject hourly = (JSONObject) json.get("hourly");

        // Precipitation units: mm
        JSONArray precipitation = (JSONArray) hourly.get("precipitation");
        List<BigDecimal> precipitationList =
                JsonTools.convertToList(precipitation);

        // Precipitation probability units: %
        JSONArray precipitationProbability =
                (JSONArray) hourly.get("precipitation_probability");
        List<Integer> precipitationProbabilityList =
                JsonTools.convertToList(precipitationProbability);

        // Relative humidity units: %
        JSONArray relativeHumidity2m =
                (JSONArray) hourly.get("relativehumidity_2m");
        List<Integer> relativeHumidity2mList =
                JsonTools.convertToList(relativeHumidity2m);

        // Temperature units: °C
        JSONArray temperature2m =
                (JSONArray) hourly.get("temperature_2m");
        List<BigDecimal> temperature2mList =
                JsonTools.convertToList(temperature2m);

        // Time units: iso8601
        JSONArray time = (JSONArray) hourly.get("time");
        List<String> timeList = JsonTools.convertToList(time);

        // UV index units: none
        JSONArray uvIndex =
                (JSONArray) hourly.get("uv_index");
        List<BigDecimal> uvIndexList = JsonTools.convertToList(uvIndex);

        // Wind speed units: km/h
        JSONArray windSpeed10m =
                (JSONArray) hourly.get("windspeed_10m");
        List<BigDecimal> windSpeed10mList =
                JsonTools.convertToList(windSpeed10m);

        List<WeatherHourly> weatherHourlyList = new ArrayList<>();
        for (int i = 0; i < time.length(); ++i) {
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
