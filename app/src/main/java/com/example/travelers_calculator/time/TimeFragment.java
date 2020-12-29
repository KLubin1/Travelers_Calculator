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

import java.time.LocalTime;
import java.time.ZoneId;
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
       //currentTime = v.findViewById(R.id.current_clock); //TODO: Were taking away the current time clock
        convertTime = v.findViewById(R.id.convert_clock);
        convertSpinner = v.findViewById(R.id.convert_spinner);
        convertTime.setIs24HourView(false);
        //convertTime.setClickable(true);

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //for each item selected, the convert clock must change its time accordingly
        //so by default, when the tab opens, it will be on local time, in which nothing will happen but display the current time
        //if the current or convert time spinners are selected to a city, the time will change according to that time.
        //so theres no need for a convert button
        //and call the conversion factory and set the hour to its corresponding city or timezone

        //for convert spinner
        Calendar c = Calendar.getInstance();

        if(convertSpinner.getSelectedItemPosition() == 0)
        {
            //display current/local time
            int hour = c.get(Calendar.HOUR_OF_DAY);
            convertTime.setHour(hour);
            //currentTime.setHour(hour);
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
        else if(convertSpinner.getSelectedItemPosition() ==13)
            convertTime.setHour(conversionFactory("Sydney"));
        else
            convertTime.setHour(12);

        //update the minutes as the hour changes to keep the time accurate
        //and update the hour to ensure that the currentTime always shows the current system time by default
        //regardless of which time zone is changed to and if its never "reset" with LocalTime
        int minute = c.get(Calendar.MINUTE);
        convertTime.setMinute(minute);
        //currentTime.setMinute(minute);
        //int hour = c.get(Calendar.HOUR_OF_DAY);
        //currentTime.setHour(hour);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //take in a string key for each city, then pass that in a switch case to return the correct hour difference
    //then pass that in the if-else ifs of onItemSelected
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int conversionFactory(String city)
    {
        //convertTime.setClickable(false);
        //Result of taking in the UTC time and adding/subtracting the offset

        //gets the calender instance of time with GMT standard, then getting hour of day
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        int UTC = c.get(Calendar.HOUR_OF_DAY);
        int ampm = c.get(Calendar.AM_PM);
        //get local time based on time zone id
        LocalTime localTime;
        //get current hour of time and return
        int offset = 0;


        switch(city)
        {
            case "New York":
                localTime = LocalTime.now(ZoneId.of("America/New_York"));
                offset = localTime.getHour();
                break;
            case "London":
                localTime = LocalTime.now(ZoneId.of("Europe/London"));
                offset = localTime.getHour();
                break;
            case "Los Angeles":
                localTime = LocalTime.now(ZoneId.of("America/Los_Angeles"));
                offset = localTime.getHour();
                break;
            case "Dubai":
                localTime = LocalTime.now(ZoneId.of("Asia/Dubai"));
                offset = localTime.getHour();
                break;
            case "Paris":
                localTime = LocalTime.now(ZoneId.of("Europe/Paris"));
                offset = localTime.getHour();
                break;
            case "Moscow":
                localTime = LocalTime.now(ZoneId.of("Europe/Moscow"));
                offset = localTime.getHour();
                break;
            case "Cairo":
                localTime = LocalTime.now(ZoneId.of("Africa/Cairo"));
                offset = localTime.getHour();
                break;
            case "Hong Kong":
                localTime = LocalTime.now(ZoneId.of("Asia/Hong_Kong"));
                offset = localTime.getHour();
                break;
            case "Beijing":
                localTime = LocalTime.now(ZoneId.of("Asia/Hong_Kong"));
                offset = localTime.getHour();
                break;
            case "New Delhi":
                localTime = LocalTime.now(ZoneId.of("Asia/Calcutta"));
                offset = localTime.getHour();
                break;
            case "Mexico City":
                localTime = LocalTime.now(ZoneId.of("America/Mexico_City"));
                offset = localTime.getHour();
                break;
            case "Brasilia":
                localTime = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
                offset = localTime.getHour();
                break;
            case "Sydney":
                localTime = LocalTime.now(ZoneId.of("Australia/Sydney"));
                offset = localTime.getHour();
                break;
        }
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