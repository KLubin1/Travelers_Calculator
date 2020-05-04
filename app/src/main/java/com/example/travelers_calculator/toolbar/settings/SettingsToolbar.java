package com.example.travelers_calculator.toolbar.settings;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.travelers_calculator.R;

public class SettingsToolbar extends AppCompatActivity
{
    //initial creation
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set back button
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);*/
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set settings page to fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();
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

    private Preference.OnPreferenceChangeListener changeListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue)
        {
            String newVal = newValue.toString();
            if(preference instanceof ListPreference)
            {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(newVal);

                // Set the summary to reflect the new value.
                //if index >=0, then get entries 'index, else return null
                preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);

                if(((ListPreference) preference).getValue() == "Orange")
                {
                    Toast.makeText(getApplicationContext(), "SunKissed", Toast.LENGTH_SHORT);
                }
            }
            return false;
        }

    };
}
