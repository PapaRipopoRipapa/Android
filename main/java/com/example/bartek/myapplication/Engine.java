package com.example.bartek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Bartek on 2016-10-28.
 */

public class Engine
{
    public static final int GAME_THREAD_DELAY = 4000;
    public static Thread musicThread;
    public static Context context;

    public boolean onExit(View v)
    {
        try
        {
            Intent music = new Intent(context, Music.class);
            context.stopService(music);
            musicThread.stop();
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
}
