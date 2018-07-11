package com.example.rus.meet14practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rus.meet14practice.DataBase.DBManager;
import com.example.rus.meet14practice.Service.MyService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //TextView textView;
    WeatherAPI.ApiInterface api;
    String text = "";

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    DBManager dbManager;

    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;

    public static final String BROADCAST = "myservice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        dbManager = new DBManager(this);
        List<WeatherDay> weatherDaysTest = dbManager.getWeatherDaysList();
        adapter.update(weatherDaysTest);
        adapter.notifyDataSetChanged();

        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);

        intentFilter = new IntentFilter(BROADCAST);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getStringExtra("result").equals("response")){
                    adapter.update(dbManager.getWeatherDaysList());
                    adapter.notifyDataSetChanged();
                }
            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}