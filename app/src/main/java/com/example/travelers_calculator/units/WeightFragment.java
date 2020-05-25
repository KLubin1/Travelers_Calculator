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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.travelers_calculator.R;

public class WeightFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add com.example.Travelers_Calculator_Demo.units code here
    private Spinner spinner1, spinner2;
    private EditText quantity;
    private TextView result;
    private TextView unitType;
    private Button convert;
    private ImageView switchButton;
    //private Button calculatorWidget;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // return inflater.inflate(R.layout.fragment_units, container, false);
        //TODO: CHANGE THE LAYOUT TO THE CLASS'S CORRESPONDING LAYOUT
        View view = inflater.inflate(R.layout.fragment_units_weight, container, false);

        //the conversion quantities
        quantity = (EditText) view.findViewById(R.id.quantity);
        result = (TextView) view.findViewById(R.id.result);
        unitType = (TextView) view.findViewById(R.id.unit_type);
        convert = (Button) view.findViewById(R.id.convertButton);
        switchButton = (ImageView) view.findViewById(R.id.switch_button);
        //calculator widget
        //calculatorWidget = (Button) view.findViewById((R.id.calculator_widget_unit));

        //for spinner 1
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.weight_spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        //for spinner 2
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.weight_spinner, android.R.layout.simple_spinner_item);
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
                result.setText((String.valueOf(conversionFactory())));
                //set the type of result
                unitType.setText(spinner2.getSelectedItem().toString());
               /* //TODO: Get history shared preference and add value of result to it
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("History", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("calcUW", result.getText().toString());
                editor.commit();*/
            }
        });

        //saving calculations
       /* calculatorWidget.setOnClickListener(new View.OnClickListener() {
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
        darkModeToggle();
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
        String errorMessage = getString(R.string.error_message);

        //from pounds
        if(spinner1.getSelectedItemPosition() == 0)
        {
            //to pounds
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 1;
            //to tons
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 0.0005;
            //to ounces
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 16;
            //to grams
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 453.59237;
            //to kilograms
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 0.0453592;
            else return 0;
        }
        //from tons
        else if(spinner1.getSelectedItemPosition() == 1)
        {
            //to pounds
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 2000;
            //to tons
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 1;
            //to ounces
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 32000;
            //to grams
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 907184.74;
            //to kilograms
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 907.18474;
            else return 0;
        }
        //from ounces
        else if(spinner1.getSelectedItemPosition() == 2)
        {
            //to pounds
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 0.0625;
            //to tons
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 0.000031;
            //to ounces
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 1;
            //to grams
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 28.349523;
            //to kilograms
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 0.02835;
            else return 0;
        }
        //from grams
        else if(spinner1.getSelectedItemPosition() == 3)
        {
            //to pounds
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 0.002205;
            //to tons
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 0.000001;
            //to ounces
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 0.035274;
            //to grams
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 1;
            //to kilograms
            else if(spinner2.getSelectedItemPosition() == 4)
                constant = 0.001;
            else return 0;
        }
        //from kilograms
        else if(spinner1.getSelectedItemPosition() == 4)
        {
            //to pounds
            if(spinner2.getSelectedItemPosition() == 0)
                constant = 2.204623;
            //to tons
            else if(spinner2.getSelectedItemPosition() == 1)
                constant = 0.001102;
            //to ounces
            else if(spinner2.getSelectedItemPosition() == 2)
                constant = 35.273962;
            //to grams
            else if(spinner2.getSelectedItemPosition() == 3)
                constant = 1000;
            //to kilograms
            else if(spinner2.getSelectedItemPosition() == 4)
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

    public void darkModeToggle()
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean darkMode = settings.getBoolean(getString(R.string.darkModeKey),false);

        if(darkMode != false)
        {
            convert.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            switchButton.setBackground(getResources().getDrawable(R.drawable.ic_white_conversion_arrow));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UnitResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unitcalc", result.getText().toString());
        editor.putInt("lspin",spinner1.getSelectedItemPosition());
        editor.putInt("rspin", spinner2.getSelectedItemPosition());
        editor.putString("unittype", unitType.getText().toString());
        editor.putString("quan", quantity.getText().toString());
        editor.commit();

    }
}
