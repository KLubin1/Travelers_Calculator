package com.example.travelers_calculator.currency;

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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;



public class CurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener
{
    private EditText quantity;
    private Spinner fromSpinner, toSpinner;
    private TextView resultView, currencyType, fromCurrencyType, toCurrencyType;
    private Button convert;

    //the final value after all the calculations. Which really doesnt even need
    //to be declared here, but whatever.
    private static double finalValue;
    private  static final String SAVE_TAG = "saved_bundle";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

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

        //saving
        if(savedInstanceState != null){
            resultView.setText(savedInstanceState.getString("Calculation"));

        }

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
                    resultView.setText(String.valueOf(roundNumber(finalValue,2)));
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                currencyType.setText(toSpinner.getSelectedItem().toString());
            }
        });

        //loading
        SharedPreferences settings = getActivity().getSharedPreferences("CurrResult", Context.MODE_PRIVATE);
        String data = settings.getString("currcalc", null);
        int fPos = settings.getInt("fspin",0);
        int tPos = settings.getInt("tspin",0);
        String quan = settings.getString("quan", null);
        String type = settings.getString("currtype", null);

        resultView.setText(data);
        fromSpinner.setSelection(fPos);
        toSpinner.setSelection(tPos);
        quantity.setText(quan);
        currencyType.setText(type);


        return  v;
    }

    //Since I cant find a way to contain the description within the spinner, then this method will be used to make a textview for each item when the item is selected
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
        else if (fromSpinner.getSelectedItemPosition() == 6)
        {
            fromCurrencyType.setText(getString(R.string.Levs));
        }
        else if (fromSpinner.getSelectedItemPosition() == 7)
        {
            fromCurrencyType.setText(getString(R.string.Korunas));
        }
        else if (fromSpinner.getSelectedItemPosition() == 8)
        {
            fromCurrencyType.setText(getString(R.string.DKrones));
        }
        else if (fromSpinner.getSelectedItemPosition() == 9)
        {
            fromCurrencyType.setText(getString(R.string.Zlotys));
        }
        else if (fromSpinner.getSelectedItemPosition() == 10)
        {
            fromCurrencyType.setText(getString(R.string.Kronas));
        }
        else if (fromSpinner.getSelectedItemPosition() == 11)
        {
            fromCurrencyType.setText(getString(R.string.Francs));
        }
        else if (fromSpinner.getSelectedItemPosition() == 12)
        {
            fromCurrencyType.setText(getString(R.string.Roubles));
        }
        else if (fromSpinner.getSelectedItemPosition() == 13)
        {
            fromCurrencyType.setText(getString(R.string.Liras));
        }
        else if (fromSpinner.getSelectedItemPosition() == 14)
        {
            fromCurrencyType.setText(getString(R.string.AusDollars));
        }
        else if (fromSpinner.getSelectedItemPosition() == 15)
        {
            fromCurrencyType.setText(getString(R.string.Reals));
        }
        else if (fromSpinner.getSelectedItemPosition() == 16)
        {
            fromCurrencyType.setText(getString(R.string.CanDollars));
        }
        else if (fromSpinner.getSelectedItemPosition() == 17)
        {
            fromCurrencyType.setText(getString(R.string.Shekels));
        }
        else if (fromSpinner.getSelectedItemPosition() == 18)
        {
            fromCurrencyType.setText(getString(R.string.Rupees));
        }
        else if (fromSpinner.getSelectedItemPosition() == 19)
        {
            fromCurrencyType.setText(getString(R.string.Wons));
        }
        else if (fromSpinner.getSelectedItemPosition() == 20)
        {
            fromCurrencyType.setText(getString(R.string.Rands));
        }
        else if (fromSpinner.getSelectedItemPosition() == 21)
        {
            fromCurrencyType.setText(getString(R.string.Rands));
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
        else if (toSpinner.getSelectedItemPosition() == 6)
        {
            toCurrencyType.setText(getString(R.string.Levs));
        }
        else if (toSpinner.getSelectedItemPosition() == 7)
        {
            toCurrencyType.setText(getString(R.string.Korunas));
        }
        else if (toSpinner.getSelectedItemPosition() == 8)
        {
            toCurrencyType.setText(getString(R.string.DKrones));
        }
        else if (toSpinner.getSelectedItemPosition() == 9)
        {
            toCurrencyType.setText(getString(R.string.Zlotys));
        }
        else if (toSpinner.getSelectedItemPosition() == 10)
        {
            toCurrencyType.setText(getString(R.string.Kronas));
        }
        else if (toSpinner.getSelectedItemPosition() == 11)
        {
            toCurrencyType.setText(getString(R.string.Francs));
        }
        else if (toSpinner.getSelectedItemPosition() == 12)
        {
            toCurrencyType.setText(getString(R.string.Roubles));
        }
        else if (toSpinner.getSelectedItemPosition() == 13)
        {
            toCurrencyType.setText(getString(R.string.Liras));
        }
        else if (toSpinner.getSelectedItemPosition() == 14)
        {
            toCurrencyType.setText(getString(R.string.AusDollars));
        }
        else if (toSpinner.getSelectedItemPosition() == 15)
        {
            toCurrencyType.setText(getString(R.string.Reals));
        }
        else if (toSpinner.getSelectedItemPosition() == 16)
        {
            toCurrencyType.setText(getString(R.string.CanDollars));
        }
        else if (toSpinner.getSelectedItemPosition() == 17)
        {
            toCurrencyType.setText(getString(R.string.Shekels));
        }
        else if (toSpinner.getSelectedItemPosition() == 18)
        {
            toCurrencyType.setText(getString(R.string.Rupees));
        }
        else if (toSpinner.getSelectedItemPosition() == 19)
        {
            toCurrencyType.setText(getString(R.string.Wons));
        }
        else if (toSpinner.getSelectedItemPosition() == 20)
        {
            toCurrencyType.setText(getString(R.string.Rands));
        }
        else if (toSpinner.getSelectedItemPosition() == 21)
        {
            toCurrencyType.setText(getString(R.string.Rands));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Number rounding to 2 decimal positions
    public static double roundNumber(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    //to save and load
    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Calculation", (resultView.getText().toString()));
    }*/

    /*@Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //getting string is null
        assert savedInstanceState != null;
        resultView.setText(String.valueOf(savedInstanceState.getBundle("Calculation")));

    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CurrResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currcalc", resultView.getText().toString());
        editor.putInt("fspin",fromSpinner.getSelectedItemPosition());
        editor.putInt("tspin", toSpinner.getSelectedItemPosition());
        editor.putString("quan", quantity.getText().toString());
        editor.putString("currtype", currencyType.getText().toString());
        editor.commit();

    }

}

