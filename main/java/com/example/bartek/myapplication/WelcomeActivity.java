package com.example.bartek.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Bartek on 2016-10-27.
 */

public class WelcomeActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splashscreen);

        Thread mainThread = new Thread()
        {
            public void run(){
                Intent mainMenu = new Intent(WelcomeActivity.this, MainMenu.class);
                WelcomeActivity.this.startActivity(mainMenu);
                WelcomeActivity.this.finish();
                overridePendingTransition(R.layout.fadein, R.layout.fadeout);
            }
        };

        new Handler().postDelayed(mainThread, Engine.GAME_THREAD_DELAY);
    }
}
