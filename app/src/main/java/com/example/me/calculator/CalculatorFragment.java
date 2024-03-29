package com.example.me.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.me.MainActivity;
import com.example.me.R;
import com.example.me.toolbar.history.HistoryData;

import java.util.ArrayList;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class CalculatorFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener // AdapterView.OnItemClickListener
 {

    //calculator data members
    //numbers
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    //operations
    private Button plus, subtract, multiply, divide, sqrt, square, base10, exponent;
    //signs
    private Button clear, dot, negative, equal, openParen, closeParen;
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
    //history data list
    private ArrayList<HistoryData> historyData;
    //constructor
    public CalculatorFragment(){}
    //to get list that holds the saved results
    ArrayList<String> calculationList =  new ArrayList<>();
    //Main Activity
     private MainActivity mainActivity;




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
        clear = (Button) view.findViewById(R.id.clear);
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
        String data = settings.getString("calc", "");
        outputResult.setText(data);
        currentDisplayedInput+=data;
        inputToBeParsed+=data;


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
        clear.setOnClickListener(this);
        clear.setOnLongClickListener(this);
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
        darkModeToggle();
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
        //input to be parsed might be a thing.
        //SharedPreferences settings = getActivity().getSharedPreferences("Conversions Storage", Context.MODE_PRIVATE);
        SharedPreferences settings = getActivity().getSharedPreferences("CalcResult", Context.MODE_PRIVATE);
        String data = settings.getString("calc", "0");
        //if(currentDisplayedInput.length() != 0)
        //{
            //inputToBeParsed = data;
           // outputResult.setText(data);
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

        if(outputResult.getText().toString().equals("Error"))
        {
            if(data.equals("C/AC"))
            {
                currentDisplayedInput = "";
                inputToBeParsed = "";
                outputResult.setText("");
            }
        }

        //for clear button
        if (data.equals("C/AC"))
        {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            boolean firstStart= sharedPreferences.getBoolean("clFirstStart", true);
            //in app oboarding for educating the user that they can tap to clear and hold to clear all
            if(firstStart == true)
            {
                MaterialTapTargetPrompt prompt = new MaterialTapTargetPrompt.Builder(this)
                        .setTarget(R.id.clear)
                        .setPrimaryText("Tap to clear; hold to clear all.")
                        //.setPromptBackground(new RectanglePromptBackground())
                        .setPromptFocal(new RectanglePromptFocal())
                        .setBackgroundColour(getResources().getColor(R.color.bold_blue))
                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                            @Override
                            public void onPromptStateChanged(@NonNull MaterialTapTargetPrompt prompt, int state) {
                                if (state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED || state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                                {
                                    prompt = null;
                                    //shared prefs
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("clFirstStart", false);
                                    editor.commit();
                                }
                            }
                        })
                        .setMaxTextWidth(R.dimen.onboarding)
                        .create();
                if (prompt != null) {
                    prompt.show();
                }
            }
            String enteredInput = outputResult.getText().toString();
            if(enteredInput.length() > 0)
            {
                enteredInput = enteredInput.substring(0, enteredInput.length() - 1);
                currentDisplayedInput = enteredInput;
                inputToBeParsed = enteredInput;
                outputResult.setText(currentDisplayedInput);
            }
        }
        //for equal button
        else if (data.equals("="))
        {
                //String enteredInput = outputResult.getText().toString();
                //enteredInput += obtainLastResult;
                // calls the function that will return the result of the calculation.
                String resultObject = mCalculator.getResult(currentDisplayedInput, inputToBeParsed);
                outputResult.setText(removeTrailingZero(resultObject));
                //shared preferences version
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("History", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("calcH", outputResult.getText().toString());
                editor.commit();


            //add the calculation to the list
           // calculationList.add(outputResult.getText().toString());

            //historyData.add(new HistoryData(outputResult.getText().toString()));

               // sharedPreferences.getString("calc", outputResult.getText().toString());


            //int entry = sharedPreferences.getInt("entry", 0);
                //getData.add(sharedPreferences.getString("calc" + i, "0"));

                //make a loop that adds 1 to the calc so that every entry has its own name,
                // so "calc0" for the first entry, "calc1" for the second entry, etc.
                //this is working, it does change the calc name
                //but this wont ever update with a actual new entry with output result, so its pretty useless
            /*for(int i = 0; i<11; ++i)
            {
                String plus = "calc" + i;
                if(data.equals("="))
                editor.putString(plus + i, outputResult.getText().toString());
            }*/
                //editor.commit();
                //so by the end here, we should have a History shared prefrence for 10 entries.
        }

        //call the parser
        else
        {
            obtainInputValues(data);
        }

    }

     //for clearing all on hold
     @Override
     public boolean onLongClick(View v)
     {
         Button button = (Button) v;
         String data = button.getText().toString();
         if(data.equals("C/AC"))
         {
             currentDisplayedInput = "";
             inputToBeParsed = "";
             outputResult.setText("");
             return true;
         }

         return false;
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


    private void changeColor()
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
                clear.setBackgroundColor(getResources().getColor(R.color.calm_rage));
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
                clear.setBackgroundColor(getResources().getColor(R.color.leaf_green));
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
                clear.setBackgroundColor(getResources().getColor(R.color.stew_green));
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
                clear.setBackgroundColor(getResources().getColor(R.color.dark_brown));
                equal.setBackgroundColor(getResources().getColor(R.color.dark_brown));
                break;
            default:
                break;

        }

    }

    private void darkModeToggle()
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean darkMode = settings.getBoolean(getString(R.string.darkModeKey),false);

        if(darkMode != false)
        {
            //the lighter toned buttons - numbers (clear orange)
            one.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            two.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            three.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            four.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            five.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            six.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            seven.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            eight.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            nine.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            zero.setBackgroundColor(getResources().getColor(R.color.dark_secondary));

            //the darker toned buttons - operations (fiery red)
            exponent.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            square.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            sqrt.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            base10.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            multiply.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            plus.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            subtract.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            divide.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            openParen.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            closeParen.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            dot.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            negative.setBackgroundColor(getResources().getColor(R.color.dark_secondary));

            //the blue buttons - AC and equal (calm rage)
            clear.setBackgroundColor(getResources().getColor(R.color.dark_secondary));
            equal.setBackgroundColor(getResources().getColor(R.color.dark_secondary));

            //making sure it sets the color to bright white  when dark mode is on
            outputResult.setTextColor(getResources().getColor(R.color.background_white));

        }
        else
            //making sure it sets the color to dark nlack when dark mode is off
          outputResult.setTextColor(getResources().getColor(R.color.black));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //save the data with shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CalcResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("calc", outputResult.getText().toString());
        editor.commit();

        //for debugging in-app onboarding
//        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
//        editor2.putBoolean("clFirstStart", true);
//        editor2.commit();

    }
    //outputResult is null, why?  Only I can think of is that by the time it reaches this point, its no longer initialized after onCreateView
    public TextView getTextView() {return outputResult; }

    public void setTextView(TextView mtextView)
    {
        this.outputResult = mtextView;
    }

    //getter for calculation list
    public ArrayList<String> getList()
    {

       return calculationList;
    }


/*
    public void setHistory()
    {
        //however this goes, all the history functionality would have to come together in here


        //for history
        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("History", Context.MODE_PRIVATE);


        //String[] letters ={"A","B","C","D", "E", "F", "G"};
        //maybe i could use a linked list instead of array list?
        LinkedList<String> data = new LinkedList<>();
        //int entry = sharedPreferences.getInt("entry", 0);

        *//*for(int i = 0; i<=entry; i++){
            data.add(sharedPreferences.getString("calc", "0"));
        }*//*

        *//*data.add(sharedPreferences.getString("calc", "0"));
        data.add(sharedPreferences.getString("calc1", "0"));
        data.add(sharedPreferences.getString("calc2", "0"));
        data.add(sharedPreferences.getString("calc3", "0"));
        data.add(sharedPreferences.getString("calc4", "0"));
        data.add(sharedPreferences.getString("calc5", "0"));
*//*
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity().getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.history_layout, null);
        //this sets the list view to the dialog box
        ListView lv = convertView.findViewById(R.id.history_list);
        alertDialog.setView(convertView);
        alertDialog.setTitle("History");

        ArrayAdapter adapter = new ArrayAdapter<>(getContext(),R.layout.history_textview, R.id.history_textview, calculationList);
        lv.setAdapter(adapter);
        alertDialog.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String calc = parent.getItemAtPosition(position).toString();
                outputResult.setText(calc);
            }
        });
        alertDialog.setCancelable(true);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }*/
}
