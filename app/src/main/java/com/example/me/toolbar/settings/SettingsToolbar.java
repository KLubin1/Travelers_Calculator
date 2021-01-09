package com.example.me.toolbar.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.me.R;

public class SettingsToolbar extends AppCompatActivity
{
    //initial creation
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//       if(toolbar != null)
//       {
//           getSupportActionBar().setHomeButtonEnabled(true);
//           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//       }
        //set back button

        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        ListPreference listPreference = new ListPreference(getApplicationContext());
        Preference preference = new Preference(getApplicationContext());
        //set settings page to fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.pref_container, new SettingFragment())
                .commit();


        //ChangeDialog changeDialog = new ChangeDialog();
        //changeDialog.show(getSupportFragmentManager(), "change dialog");

        //preference.setOnPreferenceChangeListener(changeListener);

        onColorsChanged();

    }
    //load the fragment that contains the settings view
    public static class SettingFragment extends PreferenceFragmentCompat
    {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, String rootKey) {
            //inflate layout
            setPreferencesFromResource(R.xml.setting_menu, rootKey);

        }
    }
    Preference.OnPreferenceChangeListener changeListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue)
        {
            return false;
        }
    };

    public void onColorsChanged()
    {
        //get the pref values
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String colorSelect = settings.getString(getString(R.string.colorSchemeKey),"Default Traveler");
        //so colorSelect is now holding the key for the color scheme, so now we can switch between them and change the color

        /*ChangeDialog changeDialog = new ChangeDialog();
        changeDialog.show(getSupportFragmentManager(), "change dialog");*/

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
                setTheme(R.style.NoirTheme);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }

        boolean darkMode = settings.getBoolean(getString(R.string.darkModeKey), false);

        if(darkMode != false)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
           setTheme(R.style.DarkModeTheme);
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }



}
