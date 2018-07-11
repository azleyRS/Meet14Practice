package com.example.rus.meet14practice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rus.meet14practice.databinding.WeatherDayItemBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<WeatherDay> weatherDayList;

    public MyAdapter(List<WeatherDay> weatherDayList) {
        this.weatherDayList = weatherDayList;
    }

    public MyAdapter() {
        weatherDayList = new ArrayList<>();
    }

    public void update(List<WeatherDay> weatherDayList){
        this.weatherDayList = weatherDayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherDayItemBinding weatherDayItemBinding = WeatherDayItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(weatherDayItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        WeatherDay weatherDay = weatherDayList.get(position);
        holder.bind(weatherDay);

        holder.bindPosition(position);

        final WeatherDayItemBinding weatherDayItemBinding = holder.getWeatherDayItemBinding();
        weatherDayItemBinding.setHandler(new OnItemClickHandler() {
            @Override
            public void onItemClicked() {
                Intent intent = DetailedInfoActivity.newIntent(weatherDayItemBinding.getRoot().getContext(), position);
                weatherDayItemBinding.getRoot().getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return weatherDayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        int positionForBd;

        private WeatherDayItemBinding weatherDayItemBinding;

        public MyViewHolder(WeatherDayItemBinding weatherDayItemBinding) {
            super(weatherDayItemBinding.getRoot());
            this.weatherDayItemBinding = weatherDayItemBinding;
        }
        public void bind(WeatherDay weatherDay){
            this.weatherDayItemBinding.setWeatherdayitem(weatherDay);
        }

        public WeatherDayItemBinding getWeatherDayItemBinding(){
            return weatherDayItemBinding;
        }

        public void bindPosition(int position){
            positionForBd = position;
        }
    }
}
