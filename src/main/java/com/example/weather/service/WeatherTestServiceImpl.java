package com.example.weather.service;

import lombok.AllArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class WeatherTestServiceImpl implements WeatherTestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getWeatherDaily() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_mean,precipitation_sum,windspeed_10m_max,sunrise,sunset,uv_index_max&forecast_days=16&timezone=auto";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getWeatherDailyForDay(int day) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_mean,precipitation_sum,windspeed_10m_max,sunrise,sunset,uv_index_max&forecast_days=16&timezone=auto";
        String forecast = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(forecast);
        JSONObject daily = (JSONObject) json.get("daily");

        JSONArray precipitationSum =
                JSONTools.getJSONArray(daily, "precipitation_sum");
        JSONArray precipitationProbabilityMean = JSONTools
                .getJSONArray(daily, "precipitation_probability_mean");
        JSONArray sunrise = JSONTools.getJSONArray(daily, "sunrise");
        JSONArray sunset = JSONTools.getJSONArray(daily, "sunset");
        JSONArray temperature2mMax =
                JSONTools.getJSONArray(daily, "temperature_2m_max");
        JSONArray temperature2mMin =
                JSONTools.getJSONArray(daily, "temperature_2m_min");
        JSONArray time = JSONTools.getJSONArray(daily, "time");
        JSONArray uvIndexMax = JSONTools.getJSONArray(daily, "uv_index_max");
        JSONArray windspeed10mMax =
                JSONTools.getJSONArray(daily, "windspeed_10m_max");

        return "{\"time\": \""
                + time.getString(day) + "\""
                + ", \"temperature_2m_max\": "
                + temperature2mMax.get(day)
                + ", \"temperature_2m_min\": "
                + temperature2mMin.get(day)
                + ", \"precipitation_sum\": "
                + precipitationSum.get(day)
                + ", \"precipitation_probability_mean\": "
                + precipitationProbabilityMean.get(day)
                + ", \"windspeed_10m_max\": "
                + windspeed10mMax.get(day)
                + ", \"uv_index_max\": "
                + uvIndexMax.get(day)
                + ", \"sunrise\": \""
                + sunrise.get(day) + "\""
                + ", \"sunset\": \""
                + sunset.get(day) + "\""
                + "}";
    }

    @Override
    public String getWeatherHourly() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&hourly=temperature_2m,precipitation_probability,precipitation,relativehumidity_2m,windspeed_10m,uv_index&forecast_days=1&timezone=auto";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getWeatherHourlyForHour(int hour) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&hourly=temperature_2m,precipitation_probability,precipitation,relativehumidity_2m,windspeed_10m,uv_index&forecast_days=1&timezone=auto";
        String today = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(today);
        JSONObject hourly = (JSONObject) json.get("hourly");

        JSONArray temperature2m =
                JSONTools.getJSONArray(hourly, "temperature_2m");
        JSONArray time = JSONTools.getJSONArray(hourly, "time");
        JSONArray precipitation =
                JSONTools.getJSONArray(hourly, "precipitation");
        JSONArray precipitationProbability =
                JSONTools.getJSONArray(hourly, "precipitation_probability");
        JSONArray relativeHumidity2m =
                JSONTools.getJSONArray(hourly, "relativehumidity_2m");
        JSONArray uvIndex = JSONTools.getJSONArray(hourly, "uv_index");
        JSONArray windSpeed10m =
                JSONTools.getJSONArray(hourly, "windspeed_10m");

        return "{\"time\": \""
                + time.getString(hour) + "\""
                + ", \"temperature_2m\": "
                + temperature2m.get(hour)
                + ", \"precipitation\": "
                + precipitation.get(hour)
                + ", \"precipitation_probability\": "
                + precipitationProbability.get(hour)
                + ", \"relativehumidity_2m\": "
                + relativeHumidity2m.get(hour)
                + ", \"windspeed_10m\": "
                + windSpeed10m.get(hour)
                + ", \"uv_index\": "
                + uvIndex.get(hour)
                + "}";
    }

}
