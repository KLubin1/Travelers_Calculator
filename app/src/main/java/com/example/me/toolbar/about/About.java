package com.example.me.toolbar.about;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.example.me.R;

public class About extends AppCompatActivity
{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onColorChanged();
        setContentView(R.layout.about_layout);

        //set the toolbar and set the back button action
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayShowHomeEnabled(true);
        //set title of action bar
        getSupportActionBar().setTitle("About");

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
    }

    public void onColorChanged()
    {
        //Change theme
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String colorSelect = settings.getString(getString(R.string.colorSchemeKey),"Default Traveler");
        switch (colorSelect)
        {
            case "Orange-Red":
                setTheme(R.style.SunKissedTheme);
                break;
            case "Yellow":
                setTheme(R.style.PinaColadaTheme);
                break;
            case "Green":
                setTheme(R.style.HerbivoreTheme);
                break;
            case "Dark":
                setTheme(R.style.HerbivoreTheme);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;

        }

    }
}
