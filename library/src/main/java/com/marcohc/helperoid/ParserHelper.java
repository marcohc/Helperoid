/*
 * Copyright (C) 2015 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcohc.helperoid;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserHelper {

    private static final String LOG_TAG = "ParserHelper";
    private static ObjectMapper sObjectMapper;

    public static ObjectMapper getObjectMapper() {
        if (sObjectMapper == null) {
            sObjectMapper = new ObjectMapper();
        }
        return sObjectMapper;
    }

    public static <T> T parse(Object value, Class<T> valueType) {

        T object = null;
        try {
            if (value != null && valueType != null) {
                String e = getObjectMapper().writeValueAsString(value);
                object = getObjectMapper().readValue(e, valueType);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, String.format("parse: %s", e.getMessage()));
        }
        return object;
    }

    public static <T> T parse(Object value, TypeReference<T> valueType) {

        T object = null;
        try {
            if (value != null && valueType != null) {
                String e = getObjectMapper().writeValueAsString(value);
                object = getObjectMapper().readValue(e, valueType);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, String.format("parse: %s", e.getMessage()));
        }
        return object;
    }

    public static <T extends Object> T parseJson(String jsonString, Class<T> type) {
        ObjectMapper mapper = getObjectMapper();
        T model = null;
        if (jsonString != null && !jsonString.isEmpty()) {
            try {
                model = mapper.readValue(jsonString, type);
            } catch (IOException e) {
                Log.e(LOG_TAG, String.format("parseJsonObject: %s", e.getMessage()));
            }
        }
        return model;
    }

    public static <T extends Object> T parseJson(JSONObject jsonObject, Class<T> type) {
        if (jsonObject != null) {
            return parseJson(jsonObject.toString(), type);
        } else {
            return null;
        }
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

    public static <T extends Object> List<T> parseJsonStringArray(String jsonString, Class<T> type) {
        List<T> list = new ArrayList<>();
        if (jsonString != null) {
            try {
                list = parseJsonArray(new JSONArray(jsonString), type);
            } catch (JSONException e) {
                Log.e(LOG_TAG, String.format("parseJsonStringArray: %s", e.getMessage()));
            }
        }
        return list;
    }

    public static String toJsonString(Object jsonObject) {
        String jsonString = "";
        if (jsonObject != null) {
            ObjectMapper mapper = getObjectMapper();
            try {
                jsonString = mapper.writeValueAsString(jsonObject);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.format("toJsonString: %s", e.getMessage()));
            }
        }
        return jsonString;
    }

}