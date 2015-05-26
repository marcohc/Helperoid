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

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("CommitPrefEdits")
public class PreferencesHelper {

    // ************************************************************************************************************************************************************************
    // * Attributes and constants
    // ************************************************************************************************************************************************************************

    private static final String IS_FIRST_APP_START = "is_first_app_start";
    public static final String LAST_APP_USE_KEY_PREFERENCE = "last_app_use_key_preference";
    private static SharedPreferences sharedPreferences;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public static void initialize(SharedPreferences sharedPreferencesParam) {
        sharedPreferences = sharedPreferencesParam;

        String isFirstAppStart = getString(IS_FIRST_APP_START, null);
        if (isFirstAppStart == null) {
            PreferencesHelper.putString(IS_FIRST_APP_START, "true");
        } else {
            PreferencesHelper.putString(IS_FIRST_APP_START, "false");
        }
    }

    // ************************************************************************************************************************************************************************
    // * User behaviour methods
    // ************************************************************************************************************************************************************************

    public static boolean isFirstAppInstallation() {
        return "true".equals(getString(IS_FIRST_APP_START, "true")) ? true : false;
    }

    public static boolean isFirstUseToday() {
        long lastUseTime = getLong(LAST_APP_USE_KEY_PREFERENCE, System.currentTimeMillis());
        return !DateHelper.isInTheSameDayOfCurrentDate(lastUseTime);
    }

    public static boolean isFirstUseLast24Hours() {
        long lastUseTime = getLong(LAST_APP_USE_KEY_PREFERENCE, System.currentTimeMillis());
        return DateHelper.isInLast24HoursOfCurrentDate(lastUseTime);
    }

    // ************************************************************************************************************************************************************************
    // * Shared preferences methods
    // ************************************************************************************************************************************************************************

    public static SharedPreferences getSharedPreference() {
        return sharedPreferences;
    }

    public static String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static Long getLong(String key, Long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void putLong(String key, Long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void putInt(String key, Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static void putStringList(String key, List<String> values) {
        Set<String> setValues = new HashSet<String>(values);
        sharedPreferences.edit().putStringSet(key, setValues).apply();
    }

    public static List<String> getStringList(String key) {
        List<String> stringList = new ArrayList<>();
        Set<String> setValues = sharedPreferences.getStringSet(key, null);
        if (setValues != null) {
            stringList.addAll(setValues);
        }
        return stringList;
    }

    public static void putLongList(String key, List<Long> values) {
        Set<String> setValues = new HashSet<String>(getStringListFromLongList(values));
        sharedPreferences.edit().putStringSet(key, setValues).apply();
    }

    public static List<Long> getLongList(String key) {
        List<Long> longList = new ArrayList<>();
        Set<String> setValues = sharedPreferences.getStringSet(key, null);
        if (setValues != null) {
            List<String> stringList = new ArrayList<>();
            stringList.addAll(setValues);
            longList = getLongListFromStringList(stringList);
        }
        return longList;
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary methods
    // ************************************************************************************************************************************************************************

    private static List<Long> getLongListFromStringList(List<String> stringList) {
        List<Long> longList = new ArrayList<>();
        for (String item : stringList) {
            longList.add(Long.valueOf(item));
        }
        return longList;
    }

    private static List<String> getStringListFromLongList(List<Long> longList) {
        List<String> stringList = new ArrayList<>();
        for (Long item : longList) {
            stringList.add(String.valueOf(item));
        }
        return stringList;
    }

}
