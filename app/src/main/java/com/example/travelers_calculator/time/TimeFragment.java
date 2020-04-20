package com.example.travelers_calculator.time;

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
import java.util.Date;

public class TimeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TimePicker currentTime, convertTime;
    private Spinner currentSpinner, convertSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_time, container, false);
        currentTime = v.findViewById(R.id.current_clock);
        convertTime = v.findViewById(R.id.convert_clock);
        //currentSpinner = v.findViewById(R.id.current_spinner);
        convertSpinner = v.findViewById(R.id.convert_spinner);


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
        if(convertSpinner.getSelectedItemPosition() == 0)
        {
            //display current/local time
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            convertTime.setHour(hour);
            convertTime.setMinute(minute);
            currentTime.setHour(hour);
            currentTime.setMinute(minute);
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
        int offset = 0;
        //Calendar c = Calendar.getInstance();
        //int utcOffset = c.get(Calendar.ZONE_OFFSET) + c.get(Calendar.DST_OFFSET);

        //Date::getTime allegedly uses UTC timezone.
        Date d = new Date();
        int utcTime = (int)d.getTime();

        switch(city)
        {
            case "New York":
                offset = utcTime-4;
                break;
            case "London":
                offset = utcTime+1;
                break;
            case "Los Angeles":
                offset = utcTime-7;
                break;
            case "Dubai":
                offset = utcTime+4;
                break;
            case "Paris":
                offset = utcTime+2;
                break;
            case "Moscow":
                offset = utcTime+3;
                break;
            case "Cairo":
                offset = utcTime+2;
                break;
            case "Hong Kong":
                offset = utcTime+8;
                break;
            case "Beijing":
                offset = utcTime+8;
                break;
            case "New Delhi":
                offset=utcTime+5;
                break;
            case "Mexico City":
                offset = utcTime-5;
                break;
            case "Brasilia":
                offset = utcTime-3;
                break;
        }

        return offset;

    }
}