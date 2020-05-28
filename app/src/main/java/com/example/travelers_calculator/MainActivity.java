package com.example.travelers_calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.travelers_calculator.calculator.CalculatorFragment;
import com.example.travelers_calculator.currency.CurrencyFragment;
import com.example.travelers_calculator.onboarding.Onboarding;
import com.example.travelers_calculator.time.TimeFragment;
import com.example.travelers_calculator.toolbar.about.About;
import com.example.travelers_calculator.toolbar.settings.SettingsToolbar;
import com.example.travelers_calculator.units.UnitsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, HistoryIsPressed //implements OnFragmentInteractionListener
{
 private CalculatorFragment calculatorFragment;
 private UnitsFragment unitsFragment;
 private CurrencyFragment currencyFragment;
 private long backPressed;
 private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        onColorsChanged();
        onDarkMode();
        super.onCreate(savedInstanceState);
        //setTheme(R.style.SunKissedTheme);
        setContentView(R.layout.activity_main);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.tab_navigation_bar);
        bottomNav.setOnNavigationItemSelectedListener(tabListener);


        //Change toolbar color depending on theme
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String colorSelect = settings.getString(getString(R.string.colorSchemeKey),"Default Traveler");

        //change toolbar color as there's a switch between themes
        switch (colorSelect)
        {
            case "Orange-Red":
                toolbar.setBackgroundColor(getResources().getColor(R.color.clear_orange));
                break;
            case "Yellow":
                toolbar.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                break;
            case "Green":
                toolbar.setBackgroundColor(getResources().getColor(R.color.herbal_green));
                break;
            case "Dark":
                toolbar.setBackgroundColor(getResources().getColor(R.color.downy_gray));
                break;
            default:
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;

        }
        //change toolbar color as dark mode is enabled/disabled
        boolean darkMode = settings.getBoolean(getString(R.string.darkModeKey), false);
        if(darkMode == true)
            toolbar.setBackgroundColor(getResources().getColor(R.color.dark_secondary));

        calculatorFragment = new CalculatorFragment();
        unitsFragment = new UnitsFragment();
        currencyFragment = new CurrencyFragment();

        //to clear data at start and initialize onboarding
        //if its the first time the app is opened
        SharedPreferences prefsDelete = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefsDelete.getBoolean("firstStart", true);
        SharedPreferences.Editor prefsEditor = prefsDelete.edit();
        if(firstStart)
        {
            clearData();
            //to clear data at start
            prefsEditor.putBoolean("firstStart", false);
            prefsEditor.apply();
            Intent onboard = new Intent(MainActivity.this, Onboarding.class);
            startActivity(onboard);

        }
        else
        {
            //for development purposes
            //prefsEditor.putBoolean("firstStart", true);
            //prefsEditor.apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorFragment()).commit();
        }

    }

    //switch between fragments
    private BottomNavigationView.OnNavigationItemSelectedListener tabListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId())
            {
                case R.id.navigation_calculator:
                    selectedFragment = new CalculatorFragment();
                    break;
                case R.id.navigation_units:
                    selectedFragment = new UnitsFragment();
                    break;
                case R.id.navigation_currency:
                    selectedFragment = new CurrencyFragment();
                    break;
                case R.id.navigation_time:
                    selectedFragment = new TimeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  selectedFragment).commit();
            return true;
        }
    };

    //for the toolbar menu options
    //to inflate the toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //to select the toolbar options, similar to how the fragments are selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent settings = new Intent(MainActivity.this, SettingsToolbar.class);
                startActivity(settings);
                //getSupportActionBar().setHomeButtonEnabled(true);
                //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                return true;
            case R.id.action_about:
                Intent about = new Intent(MainActivity.this, About.class);
                startActivity(about);
                return true;
            case R.id.action_history:
                setHistory();
                return  true;
            case R.id.action_exit:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearData();
        //finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //to clear the saved data of Shared Preferences
    private void clearData()
    {
        //for basic calculator
        SharedPreferences calc = getSharedPreferences("CalcResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor calcEditor = calc.edit();
        calcEditor.clear();
        calcEditor.apply();

        //for units
        SharedPreferences unit = getSharedPreferences("UnitResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor unitEditor = unit.edit();
        unitEditor.clear();
        unitEditor.apply();

        //for currency
        SharedPreferences curr = getSharedPreferences("CurrResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor currEditor = curr.edit();
        currEditor.clear();
        currEditor.apply();

        //for time
        SharedPreferences time = getSharedPreferences("TimeResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor timeEditor = time.edit();
        timeEditor.clear();
        timeEditor.apply();

        //for history
        SharedPreferences history = getSharedPreferences("History", Context.MODE_PRIVATE);
        SharedPreferences.Editor historyEditor = history.edit();
        historyEditor.clear();
        historyEditor.apply();
    }

    public void onColorsChanged()
    {
        //get the pref values
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String colorSelect = settings.getString(getString(R.string.colorSchemeKey),"Default Traveler");
        //so colorSelect is now holding the key for the color scheme, so now we can switch between them and change the color

       //TODO: Use the exact value names to pass in the case
        switch (colorSelect)
        {
            case "Orange-Red":
                setTheme(R.style.SunKissedTheme);
                //Toast.makeText(getApplicationContext(),"SunKissed", Toast.LENGTH_SHORT).show();
                break;
            case "Yellow":
                setTheme(R.style.PinaColadaTheme);
                //Toast.makeText(getApplicationContext(),"PinaColada", Toast.LENGTH_SHORT).show();
                break;
            case "Green":
                setTheme(R.style.HerbivoreTheme);
                //Toast.makeText(getApplicationContext(),"Herbivore", Toast.LENGTH_SHORT).show();
                break;
            case "Dark":
                setTheme(R.style.NoirTheme);
                //Toast.makeText(getApplicationContext(),"Noir", Toast.LENGTH_SHORT).show();
                break;
            default:
                setTheme(R.style.AppTheme);
                //Toast.makeText(getApplicationContext(),"Default", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void onDarkMode()
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean darkMode = settings.getBoolean(getString(R.string.darkModeKey), false);

        if(darkMode != false)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setTheme(R.style.DarkModeTheme);
            //Toast.makeText(getApplicationContext(), "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //Toast.makeText(getApplicationContext(), "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
        }

    }

   public String setHistory() {
       //however this goes, all the history functionality would have to come together in here

       SharedPreferences sharedPreferences = getSharedPreferences("History", MODE_PRIVATE);
       //String[] letters ={"A","B","C","D", "E", "F", "G"};
       //maybe i could use a linked list instead of array list?
       List<String> data = new ArrayList<String>();

       /*for(int i = 0; i<calculatorFragment.getList().size(); i++)
       {

       //add the stored value to the array/list
       data.add(calculatorFragment.getList().toString());
       }*/
       data.add(sharedPreferences.getString("calcH", "0"));
       AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
       LayoutInflater inflater = getLayoutInflater();
       View convertView = inflater.inflate(R.layout.history_layout, null);
       //this sets the list view to the dialog box
       ListView lv = convertView.findViewById(R.id.history_list);
       alertDialog.setView(convertView);
       alertDialog.setTitle("History");
       ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.history_textview, R.id.history_textview, data);
       lv.setAdapter(adapter);
       alertDialog.show();
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //String calc = parent.getItemAtPosition(position).toString();
               //calculatorFragment.getData(calc);

               //SharedPreferences sharedPreferences = getSharedPreferences("History", MODE_PRIVATE);
               //calculatorFragment.getTextView().setText(sharedPreferences.getString("calcH", "0"));
           }
       });
       alertDialog.setCancelable(true);


       return sharedPreferences.getString("calcH", "0");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
    }

    //so the app will only exit after two quick presses of the back button
    //to avoid accidental closing. Not that needed imo, but cool, I guess?
    @Override
    public void onBackPressed()
    {
        if(backPressed + 2000 >System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
            return;

        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressed = System.currentTimeMillis();

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        recreate();
    }
}
        /*SharedPreferences settings = getSharedPreferences("colorChanged", MODE_PRIVATE);
        boolean settingsChanged = settings.getBoolean( getString(R.string.colorSchemeKey), false);

        if(settingsChanged == true)
        {
            //recreate();
            //ChangeDialog changeDialog = new ChangeDialog();
            //changeDialog.show(getSupportFragmentManager(), "change dialog");
            Toast.makeText(getApplicationContext(), "Restarted", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor edit = settings.edit();
            edit.putBoolean("colorChanged", false);
        }
        else
            settingsChanged = false;*/


