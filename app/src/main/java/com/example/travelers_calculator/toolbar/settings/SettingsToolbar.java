package com.example.travelers_calculator.toolbar.settings;

import android.os.Bundle;

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

        ListPreference listPreference = new ListPreference(getApplicationContext());
        Preference preference = new Preference(getApplicationContext());
        //set settings page to fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();



        //ChangeDialog changeDialog = new ChangeDialog();
        //changeDialog.show(getSupportFragmentManager(), "change dialog");

        //preference.setOnPreferenceChangeListener(changeListener);


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

}
