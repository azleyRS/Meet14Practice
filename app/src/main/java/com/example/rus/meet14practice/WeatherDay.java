package com.example.rus.meet14practice;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Calendar;

public class WeatherDay extends BaseObservable {

    @SerializedName("main")
    private WeatherTemp temp;

    @SerializedName("dt")
    private long timestamp;

    public class WeatherTemp {
        Double temp;
        Double temp_min;
        Double temp_max;
        Double pressure;
        Double sea_level;
        Double grnd_level;
        Integer humidity;
        Double temp_kf;

        public WeatherTemp(Double temp, Double temp_min, Double temp_max, Double pressure, Double sea_level, Double grnd_level, Integer humidity, Double temp_kf) {
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.sea_level = sea_level;
            this.grnd_level = grnd_level;
            this.humidity = humidity;
            this.temp_kf = temp_kf;
        }
    }

    public void setTemp( Double temp, Double temp_min,
                         Double temp_max,
                         Double pressure,
                         Double sea_level,
                         Double grnd_level,
                         Integer humidity,
                         Double temp_kf) {
        this.temp = new WeatherTemp(temp, temp_min, temp_max, pressure, sea_level, grnd_level, humidity, temp_kf);
    }



    /*public Date getDate(){
        Date date = new Date(timestamp * 1000);
        return date;
    }*/

    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }

    public Calendar getDateCalendar() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }
    @Bindable
    public String getTemp() { return String.valueOf(temp.temp); }
    @Bindable
    public String getTempMin() { return String.valueOf(temp.temp_min); }
    @Bindable
    public String getTempMax() { return String.valueOf(temp.temp_max); }

    public String getDateString() {
        String res = android.text.format.DateFormat.format("EEE MMM d HH:mm:ss",getDate()).toString();
        return res;
    }

    public String getPressureString(){
        return String.valueOf(temp.pressure);
    }

    public Double getPressure() {
        return temp.pressure;
    }

    public String getSea_levelString(){
        return String.valueOf(temp.sea_level);
    }

    public Double getSea_level() {
        return temp.sea_level;
    }

    public String getGrnd_levelString(){
        return String.valueOf(temp.grnd_level);
    }

    public Double getGrnd_level() {
        return temp.grnd_level;
    }

    public String getHumidityString(){
        return String.valueOf(temp.humidity);
    }

    public Integer getHumidity() {
        return temp.humidity;
    }
    public String getTemp_kfString(){
        return String.valueOf(temp.temp_kf);
    }

    public Double getTemp_kf() {
        return temp.temp_kf;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
