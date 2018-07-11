package com.example.rus.meet14practice;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rus.meet14practice.DataBase.DBManager;
import com.example.rus.meet14practice.databinding.ActivityDetailedInfoBinding;


public class DetailedInfoActivity extends AppCompatActivity {
    private WeatherDay weatherDay;
    DBManager manager;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detailed_info);

        ActivityDetailedInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_info);

        position = getIntent().getIntExtra("position",0);

        manager = new DBManager(this);
        weatherDay = manager.getWeatherDay(position);

        //binding
        binding.setWeatherday(weatherDay);

    }



    public static Intent newIntent(Context context, int positionForBd) {
        Intent intent = new Intent(context, DetailedInfoActivity.class);
        intent.putExtra("position", positionForBd);
        return intent;
    }
}
