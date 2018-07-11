package com.example.rus.meet14practice.DataBase;

public class WeatherDbSchema {
    public static final class WeatherTable{
        public static final String NAME = "weather";

        public static final class Cols {
            public static final String DT = "dt";
            public static final String TEMP = "temperature";
            public static final String TEMP_MIN = "temp_min";
            public static final String TEMP_MAX = "temp_max";
            public static final String PRESSURE = "pressure";
            public static final String SEA_LEVEL = "sea_level";
            public static final String GRND_LEVEL = "grnd_level";
            public static final String HUMIDITY = "humidity";
            public static final String TEMP_KF = "temp_kf";
        }
    }
}
