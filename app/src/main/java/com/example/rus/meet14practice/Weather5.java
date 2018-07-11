package com.example.rus.meet14practice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather5 {
    @SerializedName("list")
    @Expose
    private List<WeatherDay> items;

    public Weather5(List<WeatherDay> items) {
        this.items = items;
    }

    public List<WeatherDay> getItems() {
        return items;
    }
}
