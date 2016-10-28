package com.example.bartek.myapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Bartek on 2016-10-29.
 */

public class Settings extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
    }
}
