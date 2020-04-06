package com.example.travelers_calculator.units;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;

public class VolumeFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add com.example.Travelers_Calculator_Demo.units code here
    private Spinner spinner1, spinner2;
    private EditText quantity;
    private TextView result;
    private TextView unitType;
    private Button convert;
    private Button calculatorWidget;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // return inflater.inflate(R.layout.fragment_units, container, false);
        //TODO: CHANGE THE LAYOUT TO THE CLASS'S CORRESPONDING LAYOUT
        View view = inflater.inflate(R.layout.fragment_units_volume, container, false);

        //the conversion quantities
        quantity = (EditText) view.findViewById(R.id.quantity);
        result = (TextView) view.findViewById(R.id.result);
        unitType = (TextView) view.findViewById(R.id.unit_type);
        convert = (Button) view.findViewById(R.id.convertButton);
        //calculator widget
        calculatorWidget = (Button) view.findViewById((R.id.calculator_widget_unit));

        //for spinner 1
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.us_units_volume, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        //for spinner 2
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.metric_units_volume, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);



        //when convert is pressed
        convert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //defaulting to 0 if there is no input
                if(quantity.getText().toString().length() == 0)
                {
                    quantity.setText("0");
                }


                //set result textview to the result
                result.setText(String.valueOf(conversionFactory()));
                //set the type of result
                unitType.setText(spinner2.getSelectedItem().toString());

                //result.setText("changed to conversion");
            }
        });

        //saving calculations
        calculatorWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //save the value
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Conversions Storage", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UniCon", result.getText().toString());
                editor.commit();

                //toast the save
                String test = "Calculation saved!";
                Toast toast = Toast.makeText(getContext(), test, Toast.LENGTH_SHORT);
                toast.show();
            }
        });




        return view;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //this is to make toast, but can put anything in here when a item is selected) maybe pull up the second spinner?
        // String text = parent.getItemAtPosition(position).toString();
       /* String errorMessage = getString(R.string.error_message);
        if(spinner1.getSelectedItemPosition() == 0)
        {
        if(spinner2.getSelectedItemPosition() == 1 || spinner2.getSelectedItemPosition() == 2)
            result.setText(errorMessage);
        else return;
        }
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void onClick(View v)
    {

    }

    //operations
    //TODO: CHANGE THE CONVERSION FACTORY TO WHATEVER UNITS THE FRAGMENT TAKES CARE OF
    @SuppressLint("SetTextI18n")
    public double conversionFactory()
    {
//        Object pound = spinner1.getItemAtPosition(0);
//        Object foot = spinner1.getItemAtPosition(1);
//        Object gallon = spinner1.getItemAtPosition(2);
//        Object gram = spinner2.getItemAtPosition(0);
//        Object meter = spinner2.getItemAtPosition(1);
//        Object liter = spinner2.getItemAtPosition(2);

        //the constant that multiplies by the quantity;
        double constant = 0;
        //the quantity variable to multiply the constant by
        double multiplier = Double.parseDouble(quantity.getText().toString());
        //not really applicable here:
       // String errorMessage = getString(R.string.error_message);

        //from gallons
        if(spinner1.getSelectedItemPosition() == 0)
        {
            //to fluid ounces
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 128;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 3785.41178;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 3.785412;
            else return 0;
        }

        //from pint
        else if(spinner1.getSelectedItemPosition() == 1)
        {
            //to fluid ounces
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 16;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 473.176473;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 0.473176;
            else return 0;
        }

        //from quarts
        else if(spinner1.getSelectedItemPosition() == 2)
        {
            //to fluid ounces
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 32;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 946.352946;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 0.946353;
            else return 0;
        }

        else
            return 0;

        //the conversion results
        double conversion = constant*multiplier;
        return conversion;
    }
}
