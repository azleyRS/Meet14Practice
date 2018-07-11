package com.example.rus.meet14practice.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "weatherBase.db";

    public WeatherBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + WeatherDbSchema.WeatherTable.NAME +
                "(" + " _id integer primary key autoincrement, " +
                WeatherDbSchema.WeatherTable.Cols.DT + ", " +
                WeatherDbSchema.WeatherTable.Cols.TEMP + ", " +
                WeatherDbSchema.WeatherTable.Cols.TEMP_MIN + ", " +
                WeatherDbSchema.WeatherTable.Cols.TEMP_MAX + ", " +
                WeatherDbSchema.WeatherTable.Cols.PRESSURE + ", " +
                WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL + ", " +
                WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL + ", " +
                WeatherDbSchema.WeatherTable.Cols.HUMIDITY + ", " +
                WeatherDbSchema.WeatherTable.Cols.TEMP_KF +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
