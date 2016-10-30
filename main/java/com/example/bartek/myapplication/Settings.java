package com.example.bartek.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Bartek on 2016-10-29.
 */

public class Settings extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
