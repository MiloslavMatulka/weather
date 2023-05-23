package com.example.weather.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class WeatherTestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/daily")
    public String getForecast() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_mean,precipitation_sum,windspeed_10m_max,sunrise,sunset,uv_index_max&forecast_days=16&timezone=auto";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/daily/{day}")
    public String getForecastDay(@PathVariable int day) {
        // Timezone: Europe/Prague
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_mean,precipitation_sum,windspeed_10m_max,sunrise,sunset,uv_index_max&forecast_days=16&timezone=auto";
        String forecast = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(forecast);
        JSONObject daily = (JSONObject) json.get("daily");

        // Time units: iso8601
        JSONArray time = (JSONArray) daily.get("time");

        // Temperature 2m max units: °C
        JSONArray temperature2mMax =
                (JSONArray) daily.get("temperature_2m_max");

        // Temperature 2m min units: °C
        JSONArray temperature2mMin =
                (JSONArray) daily.get("temperature_2m_min");

        // Precipitation probability mean units: %
        JSONArray precipitationProbabilityMean =
                (JSONArray) daily.get("precipitation_probability_mean");

        // Precipitation sum units: mm
        JSONArray precipitationSum =
                (JSONArray) daily.get("precipitation_sum");

        // Windspeed 10m max units: km/h
        JSONArray windspeed10mMax = (JSONArray) daily.get("windspeed_10m_max");

        // Sunrise units: iso8601
        JSONArray sunrise = (JSONArray) daily.get("sunrise");

        // Sunset units: iso8601
        JSONArray sunset = (JSONArray) daily.get("sunset");

        // UV index units: none
        JSONArray uvIndexMax = (JSONArray) daily.get("uv_index_max");

        return "{\"time\": \""
                + time.getString(day)
                + "\", \"temperature_2m_max\": "
                + temperature2mMax.get(day)
                + ", \"temperature_2m_min\": "
                + temperature2mMin.get(day)
                + ", \"precipitation_probability_mean\": "
                + precipitationProbabilityMean.get(day)
                + ", \"precipitation_sum\": "
                + precipitationSum.get(day)
                + ", \"windspeed_10m_max\": "
                + windspeed10mMax.get(day)
                + ", \"sunrise\": \""
                + sunrise.get(day)
                + "\", \"sunset\": \""
                + sunset.get(day)
                + "\", \"uv_index_max\": "
                + uvIndexMax.get(day)
                + "}";
    }

    @GetMapping("/hourly")
    public String getToday() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&hourly=temperature_2m,precipitation_probability,precipitation,relativehumidity_2m,windspeed_10m,uv_index&forecast_days=1&timezone=auto";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/hourly/{hour}")
    public String getTodayHour(@PathVariable int hour) {
        // Timezone: Europe/Prague
        String url = "https://api.open-meteo.com/v1/forecast?latitude=49.20&longitude=16.61&hourly=temperature_2m,precipitation_probability,precipitation,relativehumidity_2m,windspeed_10m,uv_index&forecast_days=1&timezone=auto";
        String today = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(today);
        JSONObject hourly = (JSONObject) json.get("hourly");

        // Time units: iso8601
        JSONArray time = (JSONArray) hourly.get("time");

        // Temperature units: °C
        JSONArray temperature2m = (JSONArray) hourly.get("temperature_2m");

        // Precipitation probability units: %
        JSONArray precipitationProbability =
                (JSONArray) hourly.get("precipitation_probability");

        // Precipitation units: mm
        JSONArray precipitation = (JSONArray) hourly.get("precipitation");

        // Relative humidity units: %
        JSONArray relativeHumidity2m =
                (JSONArray) hourly.get("relativehumidity_2m");

        // Wind speed units: km/h
        JSONArray windSpeed10m = (JSONArray) hourly.get("windspeed_10m");

        // UV index units: none
        JSONArray uvIndex = (JSONArray) hourly.get("uv_index");


        return "{\"time\": \""
                + time.getString(hour)
                + "\", \"temperature_2m\": "
                + temperature2m.get(hour)
                + ", \"precipitation_probability\": "
                + precipitationProbability.get(hour)
                + ", \"precipitation\": "
                + precipitation.get(hour)
                + ", \"relativehumidity_2m\": "
                + relativeHumidity2m.get(hour)
                + ", \"windspeed_10m\": "
                + windSpeed10m.get(hour)
                + ", \"uv_index\": "
                + uvIndex.get(hour)
                + "}";
    }
}
