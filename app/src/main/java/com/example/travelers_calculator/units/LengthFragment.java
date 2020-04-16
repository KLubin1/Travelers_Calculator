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

public class LengthFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add units code here
    private Spinner leftSpinner, rightSpinner;
    private EditText quantity;
    private TextView result, unitType;
    private Button convert;
    private Button switchButton;
    //private Button calculatorWidget;
    //switcher
   public FragmentSwitcher fragmentSwitcher;
   //interface
   private OnFragmentInteractionListener listener;
   //constructor, not sure if necessary
   public LengthFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // return inflater.inflate(R.layout.fragment_units, container, false);
        //TODO: CHANGE THE LAYOUT TO THE CLASS'S CORRESPONDING LAYOUT
        View view = inflater.inflate(R.layout.fragment_units_length, container, false);

        //the conversion quantities
        quantity = (EditText) view.findViewById(R.id.quantity);
        result = (TextView) view.findViewById(R.id.result);
        unitType = (TextView) view.findViewById(R.id.unit_type);
        convert = (Button) view.findViewById(R.id.convertButton);
        switchButton = (Button)view.findViewById(R.id.switch_button);
        //calculator widget
        //calculatorWidget = (Button) view.findViewById((R.id.calculator_widget_unit));

        //for spinner 1
        leftSpinner = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.length_spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(adapter1);
        leftSpinner.setOnItemSelectedListener(this);

        //for spinner 2
        rightSpinner= (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.length_spinner, android.R.layout.simple_spinner_item);
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

        //switching between views
        switchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*FragmentManager fragmentManager = new FragmentManager() {
                    @NonNull
                    @Override
                    public FragmentTransaction beginTransaction() {
                        return null;
                    }

                    @Override
                    public boolean executePendingTransactions() {
                        return false;
                    }

                    @Nullable
                    @Override
                    public Fragment findFragmentById(int id) {
                        return null;
                    }

                    @Nullable
                    @Override
                    public Fragment findFragmentByTag(@Nullable String tag) {
                        return null;
                    }

                    @Override
                    public void popBackStack() {

                    }

                    @Override
                    public boolean popBackStackImmediate() {
                        return false;
                    }

                    @Override
                    public void popBackStack(@Nullable String name, int flags) {

                    }

                    @Override
                    public boolean popBackStackImmediate(@Nullable String name, int flags) {
                        return false;
                    }

                    @Override
                    public void popBackStack(int id, int flags) {

                    }

                    @Override
                    public boolean popBackStackImmediate(int id, int flags) {
                        return false;
                    }

                    @Override
                    public int getBackStackEntryCount() {
                        return 0;
                    }

                    @NonNull
                    @Override
                    public BackStackEntry getBackStackEntryAt(int index) {
                        return null;
                    }

                    @Override
                    public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

                    }

                    @Override
                    public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

                    }

                    @Override
                    public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {

                    }

                    @Nullable
                    @Override
                    public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
                        return null;
                    }

                    @NonNull
                    @Override
                    public List<Fragment> getFragments() {
                        return null;
                    }

                    @Nullable
                    @Override
                    public SavedState saveFragmentInstanceState(@NonNull Fragment f) {
                        return null;
                    }

                    @Override
                    public boolean isDestroyed() {
                        return false;
                    }

                    @Override
                    public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {

                    }

                    @Override
                    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {

                    }

                    @Nullable
                    @Override
                    public Fragment getPrimaryNavigationFragment() {
                        return null;
                    }

                    @Override
                    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {

                    }

                    @Override
                    public boolean isStateSaved() {
                        return false;
                    }
                };*/
                if (getFragmentManager().findFragmentByTag(getString(R.string.us_switch_tag)) == null) {

                    //if the normal fragment does not exist, add it to fragment manager.
                    getFragmentManager().beginTransaction().add(R.id.length_layout, new LengthFragment(), getString(R.string.us_switch_tag)).commit();

                    //and hide the current one
                    //getFragmentManager().beginTransaction().hide(getFragmentManager().findFragmentById(R.id.length_layout)).commit();
                } else {
                    //if the normal fragment exists, show the rev layout
                    getFragmentManager().beginTransaction().show(getFragmentManager().findFragmentByTag("uSSwitch")).commit();//causing probs

                }

                if (getFragmentManager().findFragmentByTag(getString(R.string.metric_switch_tag)) != null) {
                    //if the rev fragment is visible, hide the normal layout.
                    getFragmentManager().beginTransaction().hide(getFragmentManager().findFragmentByTag("metricSwitch")).commit();
                }
                //listener.changeFragment(1);

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

   /* @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            listener = (OnFragmentInteractionListener) activity;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;

    }*/




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //this is to make toast, but can put anything in here when a item is selected) maybe pull up the second spinner?
        // String text = parent.getItemAtPosition(position).toString();
       /* String errorMessage = getString(R.string.error_message);
        if(usSpinner.getSelectedItemPosition() == 0)
        {
        if(metricSpinner.getSelectedItemPosition() == 1 || metricSpinner.getSelectedItemPosition() == 2)
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
//        Object pound = usSpinner.getItemAtPosition(0);
//        Object foot = usSpinner.getItemAtPosition(1);
//        Object gallon = usSpinner.getItemAtPosition(2);
//        Object gram = metricSpinner.getItemAtPosition(0);
//        Object meter = metricSpinner.getItemAtPosition(1);
//        Object liter = metricSpinner.getItemAtPosition(2);

        //the constant that multiplies by the quantity;
        double constant = 0;
        //the quantity variable to multiply the constant by
        double multiplier = Double.parseDouble(quantity.getText().toString());
        String errorMessage = getString(R.string.error_message);

        //from inch
        if(leftSpinner.getSelectedItemPosition() == 0)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 1;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 0.08333;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 0.02778;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 0.000016;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 2.54;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.0254;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.0000254;

            else return 0;
        }
        //from foot
        else if(leftSpinner.getSelectedItemPosition() == 1)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 12;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 1;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 0.333;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 0.000189;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 30.48;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.000189;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.000305;
            else return 0;
        }
        //from yards
        else if(leftSpinner.getSelectedItemPosition() == 2)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 36;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 3;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 1;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 0.000568;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 91.44;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.9144;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.000914;
            else return 0;
        }
        //from miles
        else if(leftSpinner.getSelectedItemPosition() == 3)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 63360;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 5280;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 1760;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 1;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 160934.4;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 1609.344;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 1.609344;
            else return 0;
        }
        //from centimeter
        else if(leftSpinner.getSelectedItemPosition() == 4)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 0.393701;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 0.032808;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 0.010936;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 9;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 1;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 0.01;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.000001;
        }
        //from meter
        else if(leftSpinner.getSelectedItemPosition() == 5)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 39.370;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 3.28084;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 1.093613;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 0.000621;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 100;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 1;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 0.001;
        }
        //from kilometer
        else if(leftSpinner.getSelectedItemPosition() == 6)
        {
            //to inch
            if(rightSpinner.getSelectedItemPosition() == 0)
                constant = 39370.0787;
            //to foot
            else if(rightSpinner.getSelectedItemPosition() == 1)
                constant = 3280.8399;
            //to yard
            else if( rightSpinner.getSelectedItemPosition() == 2)
                constant = 1093.6133;
            //to mile
            else if(rightSpinner.getSelectedItemPosition() == 3)
                constant = 0.621271;
            //to centimeter
            else if(rightSpinner.getSelectedItemPosition() == 4)
                constant = 100000;
            //to meter
            else if(rightSpinner.getSelectedItemPosition() == 5)
                constant = 1000;
            //to kilometer
            else if(rightSpinner.getSelectedItemPosition() == 6)
                constant = 1;
        }
        else
            return 0;

        //the conversion results
        double conversion = constant*multiplier;
        return conversion;
    }
}
