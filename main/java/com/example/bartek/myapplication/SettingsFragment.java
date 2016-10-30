package com.example.bartek.myapplication;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Bartek on 2016-10-30.
 */

public class SettingsFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
