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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.travelers_calculator.R;

public class VolumeFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add com.example.Travelers_Calculator_Demo.units code here
    private Spinner spinner1, spinner2;
    private EditText quantity;
    private TextView result;
    private TextView unitType;
    private Button convert;
    //private Button calculatorWidget;


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
        //calculatorWidget = (Button) view.findViewById((R.id.calculator_widget_unit));

        //for spinner 1
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.volume_spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        //for spinner 2
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.volume_spinner, android.R.layout.simple_spinner_item);
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
        /*calculatorWidget.setOnClickListener(new View.OnClickListener() {
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
        });*/

        //loading
        SharedPreferences settings = getActivity().getSharedPreferences("UnitResult", Context.MODE_PRIVATE);
        String data = settings.getString("unitcalc", null);
        int lPos = settings.getInt("lspin",0);
        int rPos = settings.getInt("rspin",0);
        String quan = settings.getString("quan", null);
        String type = settings.getString("unittype", null);

        result.setText(data);
        spinner1.setSelection(lPos);
        spinner2.setSelection(rPos);
        quantity.setText(quan);
        unitType.setText(type);

        changeColor();
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
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
        //the constant that multiplies by the quantity;
        double constant = 0;
        //the quantity variable to multiply the constant by
        double multiplier = Double.parseDouble(quantity.getText().toString());
        //not really applicable here:
       // String errorMessage = getString(R.string.error_message);

        //from gallons
        if(spinner1.getSelectedItemPosition() == 0)
        {
            //to gallons
            if(spinner2.getSelectedItemPosition()==0)
                constant = 1;
            //to pint
            else if(spinner2.getSelectedItemPosition()==1)
                constant=8;
            //to quarts
            else if(spinner2.getSelectedItemPosition()==2)
                constant = 4;
            //to fluid ounces
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 128;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 3785.41178;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 5)
                constant = 3.785412;
            else return 0;
        }

        //from pint
        else if(spinner1.getSelectedItemPosition() == 1)
        {
            //to gallons
            if(spinner2.getSelectedItemPosition()==0)
                constant = 0.125;
            //to pint
            else if(spinner2.getSelectedItemPosition()==1)
                constant=1;
            //to quarts
            else if(spinner2.getSelectedItemPosition()==2)
                constant = 0.5;
            //to fluid ounces
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 16;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 473.176473;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 5)
                constant = 0.473176;
            else return 0;
        }

        //from quarts
        else if(spinner1.getSelectedItemPosition() == 2)
        {
            //to gallons
            if(spinner2.getSelectedItemPosition()==0)
                constant = 0.25;
            //to pint
            else if(spinner2.getSelectedItemPosition()==1)
                constant=2;
            //to quarts
            else if(spinner2.getSelectedItemPosition()==2)
                constant = 1;
            //to fluid ounces
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 32;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 946.352946;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 5)
                constant = 0.946353;
            else return 0;
        }
        //from fluid ounces
        else if(spinner1.getSelectedItemPosition() == 3)
        {
            //to gallons
            if(spinner2.getSelectedItemPosition()==0)
                constant = 0.007812;
            //to pint
            else if(spinner2.getSelectedItemPosition()==1)
                constant=0.0625;
            //to quarts
            else if(spinner2.getSelectedItemPosition()==2)
                constant = 0.03125;
            //to fluid ounces
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 1;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 29.57353;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 5)
                constant = 0.029574;
            else return 0;
        }
        //from milliliters
        else if(spinner1.getSelectedItemPosition() == 4)
        {
            //to gallons
            if(spinner2.getSelectedItemPosition()==0)
                constant = 0.000264;
            //to pint
            else if(spinner2.getSelectedItemPosition()==1)
                constant=0.002113;
            //to quarts
            else if(spinner2.getSelectedItemPosition()==2)
                constant = 0.001057;
            //to fluid ounces
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 0.033814;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 1;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 5)
                constant = 0.001;
            else return 0;
        }
        //from liters
        else if(spinner1.getSelectedItemPosition() == 5)
        {
            //to gallons
            if(spinner2.getSelectedItemPosition()==0)
                constant = 0.264172;
            //to pint
            else if(spinner2.getSelectedItemPosition()==1)
                constant=2.113376;
            //to quarts
            else if(spinner2.getSelectedItemPosition()==2)
                constant = 1.056688;
            //to fluid ounces
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 33.814023;
            //to milliliters
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 1000;
            //to liters
            else if(spinner2.getSelectedItemPosition() == 5)
                constant = 1;
            else return 0;
        }

        else
            return 0;

        //the conversion results
        double conversion = constant*multiplier;
        return conversion;
    }

    public void changeColor()
    {
        //works, I just need to find out how to get it to show when the corresponding theme is on
        //get the pref values
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String colorSelect = settings.getString(getString(R.string.colorSchemeKey),"Default Traveler");
        //so colorSelect is now holding the key for the color scheme, so now we can switch between them and change the color

        switch (colorSelect)
        {
            case "Orange-Red":
                convert.setBackgroundColor(getResources().getColor(R.color.clear_orange));
                break;
            case "Yellow":
                convert.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                break;
            case "Green":
                convert.setBackgroundColor(getResources().getColor(R.color.herbal_green));
                break;
            case "Dark":
                convert.setBackgroundColor(getResources().getColor(R.color.downy_gray));
                break;
            default:
                convert.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UnitResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unitcalc", result.getText().toString());
        editor.putInt("lspin", spinner1.getSelectedItemPosition());
        editor.putInt("rspin", spinner2.getSelectedItemPosition());
        editor.putString("unittype", unitType.getText().toString());
        editor.putString("quan", quantity.getText().toString());
        editor.commit();

    }
}
