package com.example.travelers_calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.travelers_calculator.calculator.CalculatorFragment;
import com.example.travelers_calculator.currency.CurrencyFragment;
import com.example.travelers_calculator.time.TimeFragment;
import com.example.travelers_calculator.toolbar.About;
import com.example.travelers_calculator.toolbar.settings.SettingsToolbar;
import com.example.travelers_calculator.units.LengthFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity //implements OnFragmentInteractionListener
{

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

        //start on calculator by default
       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorFragment()).commit();

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

        //to clear data at start
        SharedPreferences prefsDelete = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefsDelete.getBoolean("firstStart", true);
        if(firstStart)
        {
            clearData();
            //to clear data at start
            SharedPreferences.Editor prefsEditor = prefsDelete.edit();
            prefsEditor.putBoolean("firstStart", false);
            prefsEditor.apply();

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
                    selectedFragment = new LengthFragment();
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
                Toast.makeText(getApplicationContext(),"SunKissed", Toast.LENGTH_SHORT).show();
                break;
            case "Yellow":
                setTheme(R.style.PinaColadaTheme);
                Toast.makeText(getApplicationContext(),"PinaColada", Toast.LENGTH_SHORT).show();
                break;
            case "Green":
                setTheme(R.style.HerbivoreTheme);
                Toast.makeText(getApplicationContext(),"Herbivore", Toast.LENGTH_SHORT).show();
                break;
            case "Dark":
                setTheme(R.style.NoirTheme);
                Toast.makeText(getApplicationContext(),"Noir", Toast.LENGTH_SHORT).show();
                break;
            default:
                setTheme(R.style.AppTheme);
                Toast.makeText(getApplicationContext(),"Default", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(getApplicationContext(), "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        recreate();
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

    }
}

