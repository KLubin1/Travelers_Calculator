package com.example.travelers_calculator.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.travelers_calculator.R;


public class CalculatorFragment extends Fragment implements View.OnClickListener
{
    //Add calculator code here

    //calculator data members
    //numbers
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    //operations
    private Button plus, subtract, multiply, divide, sqrt, square, base10, exponent;
    //signs
    private Button ac, dot, negative, equal, openParen, closeParen;
    //calculator widget(loads the calculation to display)
    //private Button calculatorWidgetCalc;
    //input to be parsed
    private String inputToBeParsed = "";
    //currentDisplayedInput
    private String currentDisplayedInput = "";
    //last result
    private String obtainLastResult = "";
    //output
    private TextView outputResult;
    //calculator functionality class
    private CalculatorFunctionality mCalculator;
    //constructor
    public CalculatorFragment(){}
    //shared preferences to retrieve data



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //this returns the parameters of this oncreateview method, with the layout, container, and if the saved instance state of the app was opened should be returned
        // return inflater.inflate(R.layout.fragment_calculator, container, false);

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        //hide the action bar
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        //set toolbar
        //Toolbar toolbar = view.findViewById(R.id.toolbar);
        //getActivity().setActionBar(toolbar);

        //assign the output button and text display
        outputResult = (TextView) view.findViewById(R.id.display);
        outputResult.setText("");

        mCalculator = new CalculatorFunctionality();

        //now the numbers, operations, and signs assignment
        one = (Button) view.findViewById(R.id.one);
        two = (Button) view.findViewById(R.id.two);
        three = (Button) view.findViewById(R.id.three);
        four = (Button) view.findViewById(R.id.four);
        five = (Button) view.findViewById(R.id.five);
        six = (Button) view.findViewById(R.id.six);
        seven = (Button) view.findViewById(R.id.seven);
        eight = (Button) view.findViewById(R.id.eight);
        nine = (Button) view.findViewById(R.id.nine);
        zero = (Button) view.findViewById(R.id.zero);
        plus = (Button) view.findViewById(R.id.plus);
        subtract = (Button) view.findViewById(R.id.minus);
        multiply = (Button) view.findViewById(R.id.multiply);
        divide = (Button) view.findViewById(R.id.divide);
        ac = (Button) view.findViewById(R.id.ac);
        dot = (Button) view.findViewById(R.id.dot);
        negative = (Button) view.findViewById(R.id.negative);
        equal = (Button) view.findViewById(R.id.equal);
        sqrt = (Button) view.findViewById(R.id.sqrt);
        square  = (Button) view.findViewById(R.id.square);
        base10 = (Button) view.findViewById(R.id.base10);
        exponent  = (Button) view.findViewById(R.id.exponent);
        openParen = (Button) view.findViewById(R.id.open_paren);
        closeParen = (Button) view.findViewById(R.id.close_paren);


        //widget assignment for loading conversions unto calculator
       // calculatorWidgetCalc = (Button) view.findViewById(R.id.calculator_widget_calc);
        /*calculatorWidgetCalc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //call the loaded values
                SharedPreferences settings = getActivity().getSharedPreferences("Conversions Storage", Context.MODE_PRIVATE);
                String data = settings.getString("UniCon", null);
                outputResult.setText(data);
                //toast the load
                String test = "Calculation loaded!";
                Toast toast = Toast.makeText(getContext(), test, Toast.LENGTH_SHORT);
                toast.show();

            }
        });*/

        //load the saved data with SharedPreferences
        SharedPreferences settings = getActivity().getSharedPreferences("CalcResult", Context.MODE_PRIVATE);
        String data = settings.getString("calc", null);
        outputResult.setText(data);



        //set on click listener events for numbers and operations
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        plus.setOnClickListener(this);
        subtract.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        ac.setOnClickListener(this);
        dot.setOnClickListener(this);
        equal.setOnClickListener(this);
        negative.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        square.setOnClickListener(this);
        base10.setOnClickListener(this);
        exponent.setOnClickListener(this);
        openParen.setOnClickListener(this);
        closeParen.setOnClickListener(this);

        changeColor();
        return view;
    }

    //test and pass the string values (codes) of expressions through parser
    private void obtainInputValues(String Input)
    {
        switch (Input)
        {
            case "0":
                currentDisplayedInput += "0";
                inputToBeParsed += "0";
                break;
            case "1":
                currentDisplayedInput += "1";
                inputToBeParsed +="1";
                break;
            case "2":
                currentDisplayedInput += "2";
                inputToBeParsed +="2";
                break;
            case "3":
                currentDisplayedInput += "3";
                inputToBeParsed +="3";
                break;
            case "4":
                currentDisplayedInput += "4";
                inputToBeParsed +="4";
                break;
            case "5":
                currentDisplayedInput += "5";
                inputToBeParsed +="5";
                break;
            case "6":
                currentDisplayedInput += "6";
                inputToBeParsed +="6";
                break;
            case "7":
                currentDisplayedInput += "7";
                inputToBeParsed +="7";
                break;
            case "8":
                currentDisplayedInput += "8";
                inputToBeParsed +="8";
                break;
            case "9":
                currentDisplayedInput += "9";
                inputToBeParsed +="9";
                break;
            case "+":
                currentDisplayedInput += "+";
                inputToBeParsed += "+";
                break;
            case "-":
                currentDisplayedInput += "-";
                inputToBeParsed +="-";
                break;
            case "×":
                currentDisplayedInput += "×";
                inputToBeParsed +="*";
                break;
            case "÷":
                currentDisplayedInput += "÷";
                inputToBeParsed +="/";
                break;
            case ".":
                currentDisplayedInput += ".";
                inputToBeParsed +=".";
                break;
            case "(-)":
                currentDisplayedInput += "-";
                inputToBeParsed +="-";
                break;
            case "=":
                currentDisplayedInput += "=";
                inputToBeParsed +="=";
                break;

            case "^2":
                currentDisplayedInput += "^2";
                inputToBeParsed +="^2";
                break;
            case "^":
                currentDisplayedInput += "^";
                inputToBeParsed +="^";
                break;
            case "10^":
                currentDisplayedInput += "10^";
                inputToBeParsed +="10^";
                break;
            case "√":
                currentDisplayedInput += "√";
                inputToBeParsed +="sqrt(";
                break;
            case "(":
                currentDisplayedInput += "(";
                inputToBeParsed +="(";
                break;
            case ")":
                currentDisplayedInput += ")";
                inputToBeParsed +=")";
                break;


        }


        //regarding evaluating the input
        //what im trying to say here is that if there is a placed in value without actual input, then evaluate it
        //SharedPreferences settings = getActivity().getSharedPreferences("Conversions Storage", Context.MODE_PRIVATE);
        //if(currentDisplayedInput.length() != 0)
        //{
        //mCalculator.evaluator.evaluate(currentDisplayedInput);
        // outputResult.setText(currentDisplayedInput);
        //}

        //and display the input
        outputResult.setText(currentDisplayedInput);
    }

    //maybe within this functions (or just a onClick method) we can do the advanced operations
    @Override
    public void onClick(View view)
    {
        Button button = (Button) view;
        String data = button.getText().toString();
        //to display a toast, isnt really needed
        //Toast.makeText(getContext(), "Clicked" + data, Toast.LENGTH_LONG).show();

        //for clear button
        if (data.equals("AC"))
        {

            currentDisplayedInput = "";
            inputToBeParsed = "";
            outputResult.setText("");
        }
        //for equal button
        else if (data.equals("="))
        {
            String enteredInput = outputResult.getText().toString();
            //enteredInput += obtainLastResult;
            // calls the function that will return the result of the calculation.
            String resultObject = mCalculator.getResult(currentDisplayedInput, inputToBeParsed);
            outputResult.setText(removeTrailingZero(resultObject));
        }
        //call the parser
        else
        {
            obtainInputValues(data);
        }

    }
    //subtract leading zeros (reformat numbers)
    private String removeTrailingZero(String formattingInput)
    {
        if(!formattingInput.contains("."))
        {
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if(newValue.equals(".0"))
        {
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
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
                //the lighter toned buttons - numbers (clear orange)
                one.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                two.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                three.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                four.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                five.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                six.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                seven.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                eight.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                nine.setBackgroundColor(getResources().getColor(R.color.sun_kissed));
                zero.setBackgroundColor(getResources().getColor(R.color.sun_kissed));

                //the darker toned buttons - operations (fiery red)
                exponent.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                square.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                sqrt.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                base10.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                multiply.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                plus.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                subtract.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                divide.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                openParen.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                closeParen.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                dot.setBackgroundColor(getResources().getColor(R.color.fiery_red));
                negative.setBackgroundColor(getResources().getColor(R.color.fiery_red));

                //the blue buttons - AC and equal (calm rage)
                ac.setBackgroundColor(getResources().getColor(R.color.calm_rage));
                equal.setBackgroundColor(getResources().getColor(R.color.calm_rage));

                break;
            case "Yellow":
                //the lighter toned buttons - numbers (light yellow)
                one.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                two.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                three.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                four.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                five.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                six.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                seven.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                eight.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                nine.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                zero.setBackgroundColor(getResources().getColor(R.color.light_yellow));

                //the darker toned buttons - operations (dark yellow)
                exponent.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                square.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                sqrt.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                base10.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                multiply.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                plus.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                subtract.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                divide.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                openParen.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                closeParen.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                dot.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                negative.setBackgroundColor(getResources().getColor(R.color.dark_yellow));

                //the blue buttons - AC and equal (leafy green)
                ac.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                equal.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                break;
            case "Green":
                //the lighter toned buttons - numbers (leaf green)
                one.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                two.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                three.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                four.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                five.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                six.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                seven.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                eight.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                nine.setBackgroundColor(getResources().getColor(R.color.leaf_green));
                zero.setBackgroundColor(getResources().getColor(R.color.leaf_green));

                //the darker toned buttons - operations (dark green)
                exponent.setBackgroundColor(getResources().getColor(R.color.dark_green));
                square.setBackgroundColor(getResources().getColor(R.color.dark_green));
                sqrt.setBackgroundColor(getResources().getColor(R.color.dark_green));
                base10.setBackgroundColor(getResources().getColor(R.color.dark_green));
                multiply.setBackgroundColor(getResources().getColor(R.color.dark_green));
                plus.setBackgroundColor(getResources().getColor(R.color.dark_green));
                subtract.setBackgroundColor(getResources().getColor(R.color.dark_green));
                divide.setBackgroundColor(getResources().getColor(R.color.dark_green));
                openParen.setBackgroundColor(getResources().getColor(R.color.dark_green));
                closeParen.setBackgroundColor(getResources().getColor(R.color.dark_green));
                dot.setBackgroundColor(getResources().getColor(R.color.dark_green));
                negative.setBackgroundColor(getResources().getColor(R.color.dark_green));

                //the blue buttons - AC and equal (leafy green)
                ac.setBackgroundColor(getResources().getColor(R.color.stew_green));
                equal.setBackgroundColor(getResources().getColor(R.color.stew_green));
                break;
            case "Dark":
                //the lighter toned buttons - numbers (light)
                one.setBackgroundColor(getResources().getColor(R.color.light));
                two.setBackgroundColor(getResources().getColor(R.color.light));
                three.setBackgroundColor(getResources().getColor(R.color.light));
                four.setBackgroundColor(getResources().getColor(R.color.light));
                five.setBackgroundColor(getResources().getColor(R.color.light));
                six.setBackgroundColor(getResources().getColor(R.color.light));
                seven.setBackgroundColor(getResources().getColor(R.color.light));
                eight.setBackgroundColor(getResources().getColor(R.color.light));
                nine.setBackgroundColor(getResources().getColor(R.color.light));
                zero.setBackgroundColor(getResources().getColor(R.color.light));

                //the darker toned buttons - operations (gray)
                exponent.setBackgroundColor(getResources().getColor(R.color.gray));
                square.setBackgroundColor(getResources().getColor(R.color.gray));
                sqrt.setBackgroundColor(getResources().getColor(R.color.gray));
                base10.setBackgroundColor(getResources().getColor(R.color.gray));
                multiply.setBackgroundColor(getResources().getColor(R.color.gray));
                plus.setBackgroundColor(getResources().getColor(R.color.gray));
                subtract.setBackgroundColor(getResources().getColor(R.color.gray));
                divide.setBackgroundColor(getResources().getColor(R.color.gray));
                openParen.setBackgroundColor(getResources().getColor(R.color.gray));
                closeParen.setBackgroundColor(getResources().getColor(R.color.gray));
                dot.setBackgroundColor(getResources().getColor(R.color.gray));
                negative.setBackgroundColor(getResources().getColor(R.color.gray));

                //the blue buttons - AC and equal (dark brown)
                ac.setBackgroundColor(getResources().getColor(R.color.dark_brown));
                equal.setBackgroundColor(getResources().getColor(R.color.dark_brown));
                break;
            default:
                break;

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //save the data with shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CalcResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("calc", outputResult.getText().toString());
        editor.commit();
    }

}
