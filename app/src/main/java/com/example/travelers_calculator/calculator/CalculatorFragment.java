package com.example.travelers_calculator.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;


public class CalculatorFragment extends Fragment implements View.OnClickListener
{
    //Add com.example.Travelers_Calculator_Demo.calculator code here

    /*com.example.Travelers_Calculator_Demo.calculator data members*/
    //numbers
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    //operations
    private Button plus, subtract, multiply, divide;
    //signs
    private Button ac, dot, negative, equal;
    //calculator widget(loads the calculation to display)
    //private Button calculatorWidgetCalc;
    //input to be parsed
    private String inputToBeParsed = "";
    //currentDisplayedInput
    private String currentDisplayedInput = "";
    //output
    private TextView outputResult;
    //com.example.Travelers_Calculator_Demo.calculator functionality class
    private CalculatorFunctionality mCalculator;
    //constructor
    public CalculatorFragment(){}
    //shared preferences to retrieve data



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //this returns the parameters of this oncreateview method, with the layout, container, and if the saved instance state of the last com.example.Travelers_Calculator_Demo.time the app was opened should be returned
        // return inflater.inflate(R.layout.fragment_calculator, container, false);

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        //?
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
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
        // divide.setText(Html.fromHtml(Helper.division));
        // exponent.setText(Html.fromHtml(Helper.exponent));
        ac.setOnClickListener(this);
        dot.setOnClickListener(this);
        equal.setOnClickListener(this);
        return view;
    }

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
            case "x":
                currentDisplayedInput += "x";
                inputToBeParsed +="*";
                break;
            case "/":
                currentDisplayedInput += "/";
                inputToBeParsed +="/";
                break;
            case ".":
                currentDisplayedInput += ".";
                inputToBeParsed +=".";
                break;
            case "(-)":
                currentDisplayedInput += "-";
                inputToBeParsed += "-";
            case "=":
                currentDisplayedInput += "=";
                inputToBeParsed +="=";
                break;


        }


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

        if (data.equals("AC"))
        {
            outputResult.setText("");
            currentDisplayedInput = "";
            inputToBeParsed = "";
        }
        else if (data.equals("="))
        {
            String enteredInput = outputResult.getText().toString();
            // calls the function that will return the result of the calculation.
            String resultObject = mCalculator.getResult(currentDisplayedInput, inputToBeParsed);
            outputResult.setText(removeTrailingZero(resultObject));
        }
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
}
