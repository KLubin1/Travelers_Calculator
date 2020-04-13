package com.example.travelers_calculator.currency;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyTest extends Fragment implements AdapterView.OnItemSelectedListener
{
    private EditText quantity;
    private Spinner fromSpinner, toSpinner;
    private TextView resultView, currencyType, fromCurrencyType, toCurrencyType;
    private Button convert;

    //the final value after all the calculations. Which really doesnt even need
    //to be declared here, but whatever.
    private static double finalValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_currency, container, false);


         convert = (Button) v.findViewById(R.id.convertButton);
         resultView = (TextView) v.findViewById(R.id.result);
         quantity = (EditText) v.findViewById(R.id.quantity);
         fromSpinner = (Spinner) v.findViewById(R.id.spinner1);
         toSpinner = (Spinner) v.findViewById(R.id.spinner2);
         currencyType = (TextView) v.findViewById(R.id.currency_type);
         fromCurrencyType = (TextView) v.findViewById(R.id.from_currency_type);
         toCurrencyType = (TextView) v.findViewById(R.id.to_currency_type);

        finalValue = 0.0;
        //this will probably go in the onclick event for convert button
        //resultView.setText(String.valueOf(finalValue));

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

        final Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                HttpURLConnection urlConnection = null;
                try
                {
                    try
                    {
                        //sending url request
                        String mainUrl = "https://api.exchangeratesapi.io/latest";
                        String updatedUrl = mainUrl + "?base=" + fromSpinner.getSelectedItem();
                        URL url = new URL(updatedUrl);
                        urlConnection = (HttpURLConnection) url.openConnection();

                        //string parsing
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader inReader = new BufferedReader(new InputStreamReader(in));
                        String inputLine = "";
                        String fullStr = "";
                        while ((inputLine = inReader.readLine()) != null)
                        {
                            fullStr += inputLine;
                        }

                        //getting rates and set them to the corresponding currencies in spinners
                        JSONObject jsonObj = new JSONObject(fullStr);
                        JSONObject result = jsonObj.getJSONObject("rates");

                        Double moneyValue = Double.valueOf(quantity.getText().toString());

                        if (fromSpinner.getSelectedItem().equals(toSpinner.getSelectedItem()))
                        {
                            finalValue = moneyValue;
                        }

                        else
                            {
                            Double rateValue = Double.valueOf(result.getString((String) toSpinner.getSelectedItem()));
                            Double resultValue = moneyValue * rateValue;
                            finalValue = resultValue;
                            }
                    }
                    finally
                    {
                        if (urlConnection != null)
                            urlConnection.disconnect();
                    }
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        convert.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), getString(R.string.waiting_message), Toast.LENGTH_SHORT).show();
                thread.start();
                try {
                    thread.join();
                    resultView.setText(String.valueOf(finalValue));
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                currencyType.setText(toSpinner.getSelectedItem().toString());
            }
        });

        return  v;
    }

    //TODO: Since we cant find a way to contain the description within the spinner, then maybe just make a textview for it when the item is selected?
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //from currency
        if (fromSpinner.getSelectedItemPosition() == 0)
        {
            fromCurrencyType.setText(getString(R.string.Dollars));
        }
        else if (fromSpinner.getSelectedItemPosition() == 1)
        {
            fromCurrencyType.setText(getString(R.string.Euros));
        }
        else if (fromSpinner.getSelectedItemPosition() == 2)
        {
            fromCurrencyType.setText(getString(R.string.Pounds));
        }
        else if (fromSpinner.getSelectedItemPosition() == 3)
        {
            fromCurrencyType.setText(getString(R.string.Pesos));
        }
        else if (fromSpinner.getSelectedItemPosition() == 4)
        {
            fromCurrencyType.setText(getString(R.string.Yens));
        }
        else if (fromSpinner.getSelectedItemPosition() == 5)
        {
            fromCurrencyType.setText(getString(R.string.Yuans));
        }
        //to currency
        if (toSpinner.getSelectedItemPosition() == 0)
        {
            toCurrencyType.setText(getString(R.string.Dollars));
        }
        else if (toSpinner.getSelectedItemPosition() == 1)
        {
            toCurrencyType.setText(getString(R.string.Euros));
        }
        else if (toSpinner.getSelectedItemPosition() == 2)
        {
            toCurrencyType.setText(getString(R.string.Pounds));
        }
        else if (toSpinner.getSelectedItemPosition() == 3)
        {
            toCurrencyType.setText(getString(R.string.Pesos));
        }
        else if (toSpinner.getSelectedItemPosition() == 4)
        {
            toCurrencyType.setText(getString(R.string.Yens));
        }
        else if (toSpinner.getSelectedItemPosition() == 5)
        {
            toCurrencyType.setText(getString(R.string.Yuans));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
