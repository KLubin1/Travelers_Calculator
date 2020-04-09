package com.example.travelers_calculator.currency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;

public class CurrencyFragment extends Fragment
{

    private EditText quantity;
    private Spinner fromSpinner, toSpinner;
    private TextView result, currencyType;
    private Button convert;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_currency, container, false);
        quantity = v.findViewById(R.id.quantity);
        fromSpinner = v.findViewById(R.id.spinner1);
        toSpinner = v.findViewById(R.id.spinner2);
        result = v.findViewById(R.id.result);
        currencyType = v.findViewById(R.id.currency_type);
        convert = v.findViewById(R.id.convertButton);









        return v;
    }
}
