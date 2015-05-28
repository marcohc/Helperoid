package com.marcohc.helperoid;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseHelper {

    private static final String LOG_TAG = "ParserHelper";
    private static ObjectMapper sObjectMapper;

    public static <T extends Object> T parseJson(JSONObject jsonObject, Class<T> type) {
        ObjectMapper mapper = getObjectMapper();
        T model = null;
        if (jsonObject != null) {
            try {
                model = mapper.readValue(jsonObject.toString(), type);
            } catch (IOException e) {
                Log.e(LOG_TAG, String.format("parseJsonObject: %s", e.getMessage()));
            }
        }
        return model;
    }

    public static <T extends Object> List<T> parseJsonArray(JSONArray jsonArray, Class<T> type) {
        List<T> list = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    list.add(parseJson((JSONObject) jsonArray.get(i), type));
                } catch (JSONException e) {
                    Log.e(LOG_TAG, String.format("parseJsonStringArray: %s", e.getMessage()));
                }
            }
        }
        return list;
    }

    public static <T extends Object> List<T> parseJsonStringArray(String picturesJsonString, Class<T> type) {
        List<T> list = new ArrayList<>();
        if (picturesJsonString != null) {
            try {
                list = parseJsonArray(new JSONArray(picturesJsonString), type);
            } catch (JSONException e) {
                Log.e(LOG_TAG, String.format("parseJsonStringArray: %s", e.getMessage()));
            }
        }
        return list;
    }

    public static String toJsonString(List<? extends Object> list) {
        String jsonString = "";
        if (list != null) {
            ObjectMapper mapper = getObjectMapper();
            try {
                jsonString = mapper.writeValueAsString(list);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.format("toJsonArray: %s", e.getMessage()));
            }
        }
        return jsonString;
    }

    private static ObjectMapper getObjectMapper() {
        if (sObjectMapper == null) {
            sObjectMapper = new ObjectMapper();
        }
        return sObjectMapper;
    }
}