package com.example.travelers_calculator.time;

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
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;

public class TimeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TimePicker currentTime, convertTime;
    private Spinner currentSpinner, convertSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_time, currentSpinner, false);
        currentTime = v.findViewById(R.id.current_clock);
        convertTime = v.findViewById(R.id.convert_clock);
        currentSpinner = v.findViewById(R.id.current_spinner);
        convertSpinner = v.findViewById(R.id.convert_spinner);

        //for current spinner
        currentSpinner= v.findViewById(R.id.current_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.time_cities, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentSpinner.setAdapter(adapter1);
        currentSpinner.setOnItemSelectedListener(this);

        //for convert spinner
        convertSpinner= v.findViewById(R.id.convert_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.time_cities, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertSpinner.setAdapter(adapter2);
        convertSpinner.setOnItemSelectedListener(this);


        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //TODO: all the conversion code must go here
        //for each item selected, the convert clock must change its time accordingly
        //so by default, when the tab opens, it will be on local time, in which nothing will happen but display the current time
        //if the current or convert time spinners are selected to a city, the time will change according to that time.
        //so theres no need for a convert button

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //TODO: conversion factory
    //take in a string key for each city, then pass that in a switch case to return the correct hour difference
    //then pass that in the if-else ifs of onItemSelected
    public double conversionFactory()
    {
        double diff = 0;
        return diff;

    }
}
