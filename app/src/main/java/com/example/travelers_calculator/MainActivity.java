package com.example.travelers_calculator;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.calculator.CalculatorFragment;
import com.example.travelers_calculator.currency.CurrencyFragment;
import com.example.travelers_calculator.time.TimeFragment;
import com.example.travelers_calculator.units.TemperatureFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.tab_navigation_bar);
        bottomNav.setOnNavigationItemSelectedListener(tabListener);

        //start on calculator by default
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorFragment()).commit();
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
                    selectedFragment = new TemperatureFragment();
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
}

