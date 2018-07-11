package com.example.rus.meet14practice.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rus.meet14practice.WeatherDay;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private WeatherBaseHelper weatherBaseHelper;
    public DBManager(Context context){
        this.weatherBaseHelper = new WeatherBaseHelper(context);
    }

    public void addWeatherList(List<WeatherDay> weatherDays){
        SQLiteDatabase database = null;
        try {
            database = weatherBaseHelper.getWritableDatabase();
            ContentValues contentValues = null;
            database.beginTransaction();

            for (int i = 0; i< weatherDays.size(); i++){
                contentValues = getContentValues(weatherDays.get(i), i+1);
                database.replace(WeatherDbSchema.WeatherTable.NAME, null, contentValues);
            }

            /*for (WeatherDay weatherDay : weatherDays){
                contentValues = getContentValues(weatherDay);
                //database.beginTransaction();
              //  database.insert(WeatherDbSchema.WeatherTable.NAME, null, contentValues);
                database.replace(WeatherDbSchema.WeatherTable.NAME, null, contentValues);
                //database.setTransactionSuccessful();
            }*/
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (database !=null){
                if (database.inTransaction()){
                    database.endTransaction();
                }
                database.close();
            }
        }
    }

    public List<WeatherDay> getWeatherDaysList(){
        List<WeatherDay> weatherDayList = new ArrayList<>();
        SQLiteDatabase database = null;
        try {
            database = weatherBaseHelper.getReadableDatabase();
            database.beginTransaction();
            Cursor cursor = database.query(WeatherDbSchema.WeatherTable.NAME,null,null,null,null,null,null);
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    WeatherDay weatherDay = new WeatherDay();
                    Double temp = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP)));
                    Double temp_min = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MIN)));
                    Double temp_max = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MAX)));
                    Double pressure = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.PRESSURE));
                    Double sea_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL));
                    Double grnd_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL));
                    Integer humidity = cursor.getInt(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.HUMIDITY));
                    Double temp_kf = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_KF));
                    weatherDay.setTemp(temp,temp_min,temp_max,pressure,sea_level,grnd_level,humidity,temp_kf);
                    weatherDay.setTimestamp(cursor.getLong(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.DT)));
                    weatherDayList.add(weatherDay);
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (database !=null){
                if (database.inTransaction()){
                    database.endTransaction();
                }
                database.close();
            }
        }
        return weatherDayList;
    }


    private ContentValues getContentValues(WeatherDay weatherDay, int i) {
        ContentValues values = new ContentValues();
        values.put("_id", i);
        values.put(WeatherDbSchema.WeatherTable.Cols.DT, weatherDay.getTimestamp());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP, weatherDay.getTemp());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP_MIN, weatherDay.getTempMin());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP_MAX, weatherDay.getTempMax());
        values.put(WeatherDbSchema.WeatherTable.Cols.PRESSURE, weatherDay.getPressure());
        values.put(WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL, weatherDay.getSea_level());
        values.put(WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL, weatherDay.getGrnd_level());
        values.put(WeatherDbSchema.WeatherTable.Cols.HUMIDITY, weatherDay.getHumidity());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP_KF, weatherDay.getTemp_kf());
        return values;
    }

    public WeatherDay getWeatherDay(int position) {
        WeatherDay weatherDay = new WeatherDay();
        SQLiteDatabase database = null;
        try{
            database = weatherBaseHelper.getReadableDatabase();
            database.beginTransaction();
            String whereClause = "_id = ?";
            //SQlite stars with 1, arraylist with 0
            String[] whereArgs = new String[]{String.valueOf(position + 1)};
            Cursor cursor = database.query(WeatherDbSchema.WeatherTable.NAME,null,whereClause,whereArgs,null,null,null);
            if (cursor.moveToFirst()){
                Double temp = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP)));
                Double temp_min = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MIN)));
                Double temp_max = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MAX)));
                Double pressure = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.PRESSURE));
                Double sea_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL));
                Double grnd_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL));
                Integer humidity = cursor.getInt(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.HUMIDITY));
                Double temp_kf = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_KF));
                weatherDay.setTemp(temp,temp_min,temp_max,pressure,sea_level,grnd_level,humidity,temp_kf);
                weatherDay.setTimestamp(cursor.getLong(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.DT)));
            }
            cursor.close();
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (database !=null){
                if (database.inTransaction()){
                    database.endTransaction();
                }
                database.close();
            }
        }
        return weatherDay;
    }
}
