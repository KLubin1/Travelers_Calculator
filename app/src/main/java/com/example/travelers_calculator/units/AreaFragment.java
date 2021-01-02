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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.travelers_calculator.R;

public class AreaFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add com.example.Travelers_Calculator_Demo.units code here
    private Spinner leftSpinner, rightSpinner;
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

        View view = inflater.inflate(R.layout.fragment_units_area, container, false);

        //the conversion quantities
        quantity = (EditText) view.findViewById(R.id.quantity);
        result = (TextView) view.findViewById(R.id.result);
        unitType = (TextView) view.findViewById(R.id.unit_type);
        convert = (Button) view.findViewById(R.id.convertButton);
        switchButton =(ImageView) view.findViewById(R.id.switch_button);
        //calculator widget
       // calculatorWidget = (Button) view.findViewById((R.id.calculator_widget_unit));

        //for spinner 1
        leftSpinner = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.area_spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(adapter1);
        leftSpinner.setOnItemSelectedListener(this);

        //for spinner 2
        rightSpinner = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.area_spinner, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rightSpinner.setAdapter(adapter2);
        rightSpinner.setOnItemSelectedListener(this);



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

                try
                {

                    //set result textview to the result
                    result.setText(String.valueOf(conversionFactory()));
                    //set the type of result
                    unitType.setText(rightSpinner.getSelectedItem().toString());
                    //shared preferences version
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("History", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("calcH", result.getText().toString());
                    editor.commit();
                }
                catch(NumberFormatException e)
                {
                    Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                //result.setText("changed to conversion");
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
        leftSpinner.setSelection(lPos);
        rightSpinner.setSelection(rPos);
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
    @SuppressLint("SetTextI18n")
    public double conversionFactory()
    {
        //the constant that multiplies by the quantity;
        double constant = 0;
        //the quantity variable to multiply the constant by
        double multiplier = Double.parseDouble(quantity.getText().toString());
        String errorMessage = getString(R.string.error_message);

        //from inches²
        if(leftSpinner.getSelectedItemPosition() == 0)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 1;
            //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 0.006944;
            //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 0.000772;
            //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 2.491e-10;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 6.4516;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.000645;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 6.4516e-10;
            else return 0;
        }

        //from feet²
        else if(leftSpinner.getSelectedItemPosition() == 1)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 144;
            //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 1;
            //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 0.11111;
            //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 3.587e-8;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 929.0304;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.092903;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 9.2903e-8;
            else return 0;
        }

        //from yards²
        else if(leftSpinner.getSelectedItemPosition() == 2)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 1296;
            //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 9;
            //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 1;
            //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 3.2283e-7;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 8361.2736;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.836127;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 8.3613e-7;
            else return 0;
        }

        //from miles²
        else if(leftSpinner.getSelectedItemPosition() == 3)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 4014489600.0;
                //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 27878400;
                //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 3097600;
                //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 1;
                //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 2.5900e+10;
                //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 2589988.11;
                //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.000001;
            else return 0;
        }
        //from centimeter²
        else if(leftSpinner.getSelectedItemPosition() == 4)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 0.155;
            //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 0.001076;
            //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 0.0012;
            //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 3.861e-11;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 1;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.0001;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 1.01e-10;
            else return 0;
        }
        //from meters²
        else if(leftSpinner.getSelectedItemPosition() == 5)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 1550.0031;
                //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 10.76391;
                //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 1.19599;
                //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 3.861e-7;
                //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 10000;
                //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 1;
                //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.000001;
            else return 0;
        }
        //from kilometers²
        else if(leftSpinner.getSelectedItemPosition() == 6)
        {
            //to inches²
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 1550003100.0;
            //to feet²
            else if (rightSpinner.getSelectedItemPosition() == 1)
                constant = 10763910.4;
            //to yards²
            else if(rightSpinner.getSelectedItemPosition() == 2)
                constant = 1195990.5;
            //to miles²
            else if (rightSpinner.getSelectedItemPosition() == 3)
                constant = 0.386102;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 1.0000e+12;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 1000000;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
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
        editor.putInt("lspin",leftSpinner.getSelectedItemPosition());
        editor.putInt("rspin", rightSpinner.getSelectedItemPosition());
        editor.putString("unittype", unitType.getText().toString());
        editor.putString("quan", quantity.getText().toString());
        editor.commit();

    }

}
