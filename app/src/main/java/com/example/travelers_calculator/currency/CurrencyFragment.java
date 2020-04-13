package com.example.travelers_calculator.currency;

import android.os.Bundle;
import android.util.Log;
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

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{

    private EditText quantity;
    private Spinner fromSpinner, toSpinner;
    private TextView resultView, currencyType;
    private Button convert;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_currency, container, false);
        quantity = v.findViewById(R.id.quantity);
        fromSpinner = v.findViewById(R.id.spinner1);
        toSpinner = v.findViewById(R.id.spinner2);
        resultView = v.findViewById(R.id.result);
        currencyType = v.findViewById(R.id.currency_type);
        convert = v.findViewById(R.id.convertButton);

        //for spinner 1
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.currencies_list, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter1);
        fromSpinner.setOnItemSelectedListener(this);

        //for spinner 2
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.currencies_list, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(adapter2);
        toSpinner.setOnItemSelectedListener(this);



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
                try
                {
                    resultView.setText(String.valueOf(conversionFactory()));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                //set the type of result
                currencyType.setText(toSpinner.getSelectedItem().toString());

                //result.setText("changed to conversion");
            }
        });



        return v;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public double conversionFactory() throws IOException {
        //the constant that multiplies by the quantity;
        double rate = 0;
        //the quantity variable to multiply the constant by
        double multiplier = Double.parseDouble(quantity.getText().toString());
        String errorMessage = getString(R.string.error_message);

        //from dollar
        if(fromSpinner.getSelectedItemPosition() == 0)
        {
            //to dollar
            if(toSpinner.getSelectedItemPosition() == 0)
                rate = 1;
            //to euro
            //TEST
            else if(toSpinner.getSelectedItemPosition() == 1)
                rate = (convertCurrency(getString(R.string.USDtoEUR)));
            //to pound
            else if(toSpinner.getSelectedItemPosition() == 2)
                rate = 6.87;
            //to peso
            else if(toSpinner.getSelectedItemPosition() == 3)
                rate = 6.90;
            //to yen
            else if(toSpinner.getSelectedItemPosition() == 4)
                rate = 5.89;
            //to yuan
            else if(toSpinner.getSelectedItemPosition() == 5)
                rate = 3.02;

            else return 0;
        }

        //from euro
        else if(fromSpinner.getSelectedItemPosition() == 1)
        {
            //to dollar
            if(toSpinner.getSelectedItemPosition() == 0)
                rate = 1.09;
            //to euro
            else if(toSpinner.getSelectedItemPosition() == 1)
                rate = 1;
            //to pound
            else if(toSpinner.getSelectedItemPosition() == 2)
                rate = 2.08;
            //to peso
            else if(toSpinner.getSelectedItemPosition() == 3)
                rate = 5.90;
            //to yen
            else if(toSpinner.getSelectedItemPosition() == 4)
                rate = 4.98;
            //to yuan
            else if(toSpinner.getSelectedItemPosition() == 5)
                rate = 5.89;
            else return 0;
        }

        //from pound
        else if(fromSpinner.getSelectedItemPosition() == 2)
        {
            //to dollar
            if(toSpinner.getSelectedItemPosition() == 0)
                rate = 1.08;
            //to euro
            else if(toSpinner.getSelectedItemPosition() == 1)
                rate = 2.09;
            //to pound
            else if(toSpinner.getSelectedItemPosition() == 2)
                rate = 1;
            //to peso
            else if(toSpinner.getSelectedItemPosition() == 3)
                rate = 7.89;
            //to yen
            else if(toSpinner.getSelectedItemPosition() == 4)
                rate = 3.90;
            //to yuan
            else if(toSpinner.getSelectedItemPosition() == 5)
                rate = 3.0;
            else return 0;

        }
        //from peso
        else if(fromSpinner.getSelectedItemPosition() == 3)
        {
            //to dollar
            if(toSpinner.getSelectedItemPosition() == 0)
                rate = 0.980;
            //to euro
            else if(toSpinner.getSelectedItemPosition() == 1)
                rate = 0.0254;
            //to pound
            else if(toSpinner.getSelectedItemPosition() == 2)
                rate = 0.0000254;
            //to peso
            else if(toSpinner.getSelectedItemPosition() == 3)
                rate = 1;
            //to yen
            else if(toSpinner.getSelectedItemPosition() == 4)
                rate = 0.0000254;
            //to yuan
            else if(toSpinner.getSelectedItemPosition() == 5)
                rate = 0.0000254;
            else return 0;

        }

        //from yen
        else if(fromSpinner.getSelectedItemPosition() == 4)
        {
            //to dollar
            if(toSpinner.getSelectedItemPosition() == 0)
                rate = 0.980;
                //to euro
            else if(toSpinner.getSelectedItemPosition() == 1)
                rate = 0.0254;
                //to pound
            else if(toSpinner.getSelectedItemPosition() == 2)
                rate = 0.0000254;
                //to peso
            else if(toSpinner.getSelectedItemPosition() == 3)
                rate = 0.980;
                //to yen
            else if(toSpinner.getSelectedItemPosition() == 4)
                rate = 1;
                //to yuan
            else if(toSpinner.getSelectedItemPosition() == 5)
                rate = 0.0000254;
            else return 0;

        }
        //from yuan
        else if(fromSpinner.getSelectedItemPosition() == 5)
        {
            //to dollar
            if(toSpinner.getSelectedItemPosition() == 0)
                rate = 0.980;
                //to euro
            else if(toSpinner.getSelectedItemPosition() == 1)
                rate = 0.0254;
                //to pound
            else if(toSpinner.getSelectedItemPosition() == 2)
                rate = 0.0000254;
                //to peso
            else if(toSpinner.getSelectedItemPosition() == 3)
                rate = 0.758;
                //to yen
            else if(toSpinner.getSelectedItemPosition() == 4)
                rate = 0.0000254;
                //to yuan
            else if(toSpinner.getSelectedItemPosition() == 5)
                rate = 1;
            else return 0;

        }

        else
            return 0;

        //the conversion results
        double conversion = rate*multiplier;
        return conversion;
    }

    public double convertCurrency(final String urlRequest) throws IOException
    {
        String mainUrl = "https://api.exchangeratesapi.io/latest";
        String updatedUrl =mainUrl + "?base" +fromSpinner.getSelectedItem();
        String url = updatedUrl;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e)
            {
                String message = e.getMessage().toString();
                Log.w("failure Response", message);
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException
            {
                final String message = response.body().string();
                getActivity().runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        // TODO: Change this function to be able to convert between the two types
                        try
                        {
                            JSONObject obj = new JSONObject(message);
                            //returns rates
                            JSONObject result = obj.getJSONObject("rates");

                            // quantity input
                            double multiplier = Double.parseDouble(quantity.getText().toString());

                            //final result
                            double finResult = 0.0;

                            //gets the exchange rates of the currencies from this spinner
                            //String fromVal = result.getString(fromSpinner.toString());
                            //String toVal = result.getString(toSpinner.toString());

                            //But since were just comparing between two rates, it should just return that answer and that's it
                            //So I just need to be able to return the rate, that's it
                            //double rate = Double.valueOf(result);

                            //double rate = 0;
                            //the quantity variable to multiply the constant by

                            String errorMessage = getString(R.string.error_message);

                            //from dollar
                            if(fromSpinner.getSelectedItem().equals(toSpinner.getSelectedItem()))
                            {
                                finResult = multiplier;
                            }
                            else
                                {
                                Double rateValue = Double.valueOf(result.getString((String)toSpinner.getSelectedItem()));
                                Double resultValue = multiplier * rateValue;
                                finResult = resultValue;
                                }

                            //the conversion results
                            //double conversion = rate*multiplier;
                            //then outputs the quantity value times the value of rates
                            //double output = dollarval*Double.valueOf(val);

                            //result.setText(String.valueOf(output));



                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });
        return 0;
    }
}


