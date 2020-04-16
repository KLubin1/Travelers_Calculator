package com.example.travelers_calculator.units;

import android.annotation.SuppressLint;
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

import com.example.travelers_calculator.R;

public class AreaFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add com.example.Travelers_Calculator_Demo.units code here
    private Spinner leftSpinner, rightSpinner;
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
        View view = inflater.inflate(R.layout.fragment_units_area, container, false);

        //the conversion quantities
        quantity = (EditText) view.findViewById(R.id.quantity);
        result = (TextView) view.findViewById(R.id.result);
        unitType = (TextView) view.findViewById(R.id.unit_type);
        convert = (Button) view.findViewById(R.id.convertButton);
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


                //set result textview to the result
                result.setText(String.valueOf(conversionFactory()));
                //set the type of result
                unitType.setText(rightSpinner.getSelectedItem().toString());

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
                constant = 2.491E-10;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 6.4516;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.000645;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 6.4516E-10;
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
                constant = 3.587E-8;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 929.0304;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.092903;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 9.2903E-8;
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
                constant = 3.2283E-7;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 8361.2736;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.836127;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 8.3613E-7;
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
                constant = 2.5900E+10;
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
                constant = 3.861E-11;
            //to centimeters²
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 1;
            //to meters²
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.0001;
            //to kilometers²
            else if( rightSpinner.getSelectedItemPosition() == 6)
                constant = 1.01E-10;
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
                constant = 3.861E-7;
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
                constant = 1.0000E+12;
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
}
