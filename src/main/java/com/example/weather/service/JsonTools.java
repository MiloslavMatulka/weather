package com.example.weather.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTools {

    @SuppressWarnings("unchecked")
    static <T> List<T> convertToList(JSONArray jsonArray) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (!jsonArray.get(i).equals(JSONObject.NULL)) {
                list.add((T) jsonArray.get(i));
            } else {
                list.add(null);
            }
        }
        return list;
    }
}
