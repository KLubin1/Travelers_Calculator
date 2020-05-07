package com.example.travelers_calculator.time;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TimePicker currentTime, convertTime;
    private Spinner convertSpinner;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_time, container, false);
        currentTime = v.findViewById(R.id.current_clock);
        convertTime = v.findViewById(R.id.convert_clock);
        convertSpinner = v.findViewById(R.id.convert_spinner);

        currentTime.setIs24HourView(false);
        convertTime.setIs24HourView(false);
        convertTime.setClickable(false);

        //for current spinner
        //currentSpinner= v.findViewById(R.id.current_spinner);
        //ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.time_cities, android.R.layout.simple_spinner_item);
        //adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //currentSpinner.setAdapter(adapter1);
        //currentSpinner.setOnItemSelectedListener(this);


        //for convert spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.time_cities, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertSpinner.setAdapter(adapter2);
        convertSpinner.setOnItemSelectedListener(this);


        //saving
        SharedPreferences settings = getActivity().getSharedPreferences("TimeResult", Context.MODE_PRIVATE);
        int city = settings.getInt("city",0);
        convertSpinner.setSelection(city);

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //TODO: the tying-up of the conversion code must go here
        //for each item selected, the convert clock must change its time accordingly
        //so by default, when the tab opens, it will be on local time, in which nothing will happen but display the current time
        //if the current or convert time spinners are selected to a city, the time will change according to that time.
        //so theres no need for a convert button
        //and call the conversion factory and set the hour to its corresponding city or timezone

        //for current spinner
        /*if(currentSpinner.getSelectedItemPosition() == 0)
        {
          //display current/local time
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            currentTime.setHour(hour);
            currentTime.setMinute(minute);
        }
        else if(currentSpinner.getSelectedItemPosition()== 1)
            currentTime.setHour(conversionFactory("New York"));
        else if(currentSpinner.getSelectedItemPosition()== 2)
            currentTime.setHour(conversionFactory("London"));
        else if(currentSpinner.getSelectedItemPosition()== 3)
            currentTime.setHour(conversionFactory("Los Angeles"));
        else if(currentSpinner.getSelectedItemPosition() ==4)
            currentTime.setHour(conversionFactory("Dubai"));
        else if(currentSpinner.getSelectedItemPosition() ==5)
            currentTime.setHour(conversionFactory("Paris"));
        else if(currentSpinner.getSelectedItemPosition() ==6)
            currentTime.setHour(conversionFactory("Moscow"));
        else if(currentSpinner.getSelectedItemPosition() ==7)
            currentTime.setHour(conversionFactory("Cairo"));
        else if(currentSpinner.getSelectedItemPosition() ==8)
            currentTime.setHour(conversionFactory("Beijing"));
        else if(currentSpinner.getSelectedItemPosition() ==9)
            currentTime.setHour(conversionFactory("Hong Kong"));
        else if(currentSpinner.getSelectedItemPosition() ==10)
            currentTime.setHour(conversionFactory("Brasilia"));
        else if(currentSpinner.getSelectedItemPosition() ==11)
            currentTime.setHour(conversionFactory("New Delhi"));
        else if(currentSpinner.getSelectedItemPosition() ==12)
            currentTime.setHour(conversionFactory("Mexico City"));
*/
        //for convert spinner
        Calendar c = Calendar.getInstance();

        if(convertSpinner.getSelectedItemPosition() == 0)
        {
            //display current/local time
            int hour = c.get(Calendar.HOUR_OF_DAY);
            convertTime.setHour(hour);
            currentTime.setHour(hour);
        }
        else if(convertSpinner.getSelectedItemPosition()== 1)
            convertTime.setHour(conversionFactory("New York"));
        else if(convertSpinner.getSelectedItemPosition()== 2)
            convertTime.setHour(conversionFactory("London"));
        else if(convertSpinner.getSelectedItemPosition()== 3)
            convertTime.setHour(conversionFactory("Los Angeles"));
        else if(convertSpinner.getSelectedItemPosition() ==4)
            convertTime.setHour(conversionFactory("Dubai"));
        else if(convertSpinner.getSelectedItemPosition() ==5)
            convertTime.setHour(conversionFactory("Paris"));
        else if(convertSpinner.getSelectedItemPosition() ==6)
            convertTime.setHour(conversionFactory("Moscow"));
        else if(convertSpinner.getSelectedItemPosition() ==7)
            convertTime.setHour(conversionFactory("Cairo"));
        else if(convertSpinner.getSelectedItemPosition() ==8)
            convertTime.setHour(conversionFactory("Beijing"));
        else if(convertSpinner.getSelectedItemPosition() ==9)
            convertTime.setHour(conversionFactory("Hong Kong"));
        else if(convertSpinner.getSelectedItemPosition() ==10)
            convertTime.setHour(conversionFactory("Brasilia"));
        else if(convertSpinner.getSelectedItemPosition() ==11)
            convertTime.setHour(conversionFactory("New Delhi"));
        else if(convertSpinner.getSelectedItemPosition() ==12)
            convertTime.setHour(conversionFactory("Mexico City"));
        else
            convertTime.setHour(12);

        //update the minutes as the hour changes to keep the time accurate
        //and update the hour to ensure that the currentTime always shows the current system time by default
        //regardless of which time zone is changed to and if its never "reset" with LocalTime
        int minute = c.get(Calendar.MINUTE);
        convertTime.setMinute(minute);
        currentTime.setMinute(minute);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        currentTime.setHour(hour);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //TODO: conversion factory
    //take in a string key for each city, then pass that in a switch case to return the correct hour difference
    //then pass that in the if-else ifs of onItemSelected
    public int conversionFactory(String city)
    {
        //Result of taking in the UTC time and adding/subtracting the offset
        int offsetTime;
        int offset = 0;

        //currentTime.setIs24HourView(true);
        //convertTime.setIs24HourView(true);

        //gets the calender instance of time with GMT standard, then getting hour of day
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        int UTC = c.get(Calendar.HOUR_OF_DAY);
        int ampm = c.get(Calendar.AM_PM);


        switch(city)
        {
            case "New York":
                offset = UTC-4;
                break;
            case "London":
                offset = UTC+1;
                break;
            case "Los Angeles":
                offset = UTC-7;
                break;
            case "Dubai":
                offset= UTC+4;
                break;
            case "Paris":
                offset = UTC+2;
                break;
            case "Moscow":
                offset = UTC+3;
                break;
            case "Cairo":
                offset = UTC+2;
                break;
            case "Hong Kong":
                offset = UTC+8;
                break;
            case "Beijing":
                offset = UTC+8;
                break;
            case "New Delhi":
                offset= UTC+5;
                break;
            case "Mexico City":
                offset = UTC-5;
                break;
            case "Brasilia":
                offset = UTC-3;
                break;
        }

        //if the offset is in the AM
        if(offset < 12)
        {
            //set am
            offset = offset+12;
        }
        //if the offset is in the PM
        else if(offset > 12)
        {
            offset = offset-12;
        }
        else
            offset = 12;

        return offset;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TimeResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("city", convertSpinner.getSelectedItemPosition());
        editor.commit();
    }
}