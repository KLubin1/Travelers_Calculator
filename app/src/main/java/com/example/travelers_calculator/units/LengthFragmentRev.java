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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.travelers_calculator.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class LengthFragmentRev extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    //Add units code here
    private Spinner usSpinner, metricSpinner;
    private EditText quantity;
    private TextView result, unitType;
    private Button convert;
    //private Button calculatorWidget;
    //TODO: ADD THE ARROW BUTTON
    //TODO: CALL AND INSTANTIATE THE FRAGMENTSWITCHER CLASS
    private Button switchButton;
    public FragmentSwitcher fragmentSwitcher;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // return inflater.inflate(R.layout.fragment_units, container, false);
        //TODO: CHANGE THE LAYOUT TO THE CLASS'S CORRESPONDING LAYOUT
        final View view = inflater.inflate(R.layout.fragment_units_length_rev, container, false);

        //the conversion quantities
        quantity = (EditText) view.findViewById(R.id.quantity);
        result = (TextView) view.findViewById(R.id.result);
        unitType = (TextView) view.findViewById(R.id.unit_type);
        convert = (Button) view.findViewById(R.id.convertButton);
        switchButton = (Button)view.findViewById(R.id.rev_switch_button);
        //calculator widget
        //calculatorWidget = (Button) view.findViewById((R.id.calculator_widget_unit));

        //TODO: SWITCH SPINNER 1'S LAYOUT TO SPINNER 2'S AND VICEVERSA
        //for spinner 2
        usSpinner = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.us_units_length, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usSpinner.setAdapter(adapter1);
        usSpinner.setOnItemSelectedListener(this);

        //for spinner 1
        metricSpinner = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.metric_units_length, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metricSpinner.setAdapter(adapter2);
        metricSpinner.setOnItemSelectedListener(this);



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
                unitType.setText(usSpinner.getSelectedItem().toString());

                //result.setText("changed to conversion");
            }
        });

        // TODO: ADD THE ARROW BUTTONS ON CLICK LISTENER EVENT AND CALL FRAGMENTSWITCHER
        switchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = new FragmentManager() {
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
                };
                if (getFragmentManager().findFragmentById(R.id.length_layout_rev)!= null)
                {
                    //if the fragment exists, show the normal one
                    getFragmentManager().beginTransaction().show(getFragmentManager().findFragmentById(getId())).commit();
                    //and hide the rev one
                    //getFragmentManager().beginTransaction().hide(fragmentManager.findFragmentById(R.id.length_layout_rev)).commit();
                }
                else
                {
                    //if the fragment does not exist, add it to fragment manager.
                    getFragmentManager().beginTransaction().add(R.id.length_layout_rev, new LengthFragmentRev()).commit();

                }

                if (getFragmentManager().findFragmentById(R.id.length_layout) != null)
                {
                    //if the other fragment is visible, hide the current layout
                    getFragmentManager().beginTransaction().hide(fragmentManager.findFragmentById(R.id.length_layout_rev)).commit();

                }
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
    ////TODO: CHANGE THE CONVERSION FACTORY SPINNER 2 TO SPINNER 1
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

        //from centimeter
        if(metricSpinner.getSelectedItemPosition() == 0)
        {
            //to inch
            if(usSpinner.getSelectedItemPosition() == 0)
                constant = 0.3937;
            //to feet
            else if(usSpinner.getSelectedItemPosition() == 1)
                constant = 0.0328;
            //to yard
            else if(usSpinner.getSelectedItemPosition() == 2)
                    constant = 0.0103;
            //to mile
            else if(usSpinner.getSelectedItemPosition() ==3)
                constant = 0.0000006;
            else return 0;
        }

        //from meter
        else if(metricSpinner.getSelectedItemPosition() == 1)
        {
            //to inch
            if(usSpinner.getSelectedItemPosition() == 0)
                constant = 30.48;
            //to feet
            else if(usSpinner.getSelectedItemPosition() == 1)
                constant = 0.3048;
            //to yard
            else if(usSpinner.getSelectedItemPosition() == 2)
                    constant = 0.0003048;
            //to mile
            else if(usSpinner.getSelectedItemPosition() == 3)
                constant = 0.09;
            else return 0;
        }

        //from kilometer
        else if(metricSpinner.getSelectedItemPosition() == 2)
        {
            //to inch
            if(usSpinner.getSelectedItemPosition() == 0)
                constant = 91.44;
            //to feet
            else if(usSpinner.getSelectedItemPosition() == 1)
                constant = 0.9144;
            //to yard
            else if(usSpinner.getSelectedItemPosition() == 2)
                constant = 0.000914;
            else if(usSpinner.getSelectedItemPosition()==3)
                constant = 0.98;
            else return 0;
        }


        else
            return 0;

        //the conversion results
        double conversion = constant*multiplier;
        return conversion;
    }
}
