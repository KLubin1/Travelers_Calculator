package com.example.travelers_calculator.time;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;

public class TimeFragment extends Fragment
{
    private TimePicker currentTime, convertTime;
    private Spinner currentSpinner, convertSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_time, container, false);
        currentTime = v.findViewById(R.id.current_clock);
        convertTime = v.findViewById(R.id.convert_clock);
        currentSpinner = v.findViewById(R.id.current_spinner);
        convertSpinner = v.findViewById(R.id.convert_spinner);

        return v;
    }
}
