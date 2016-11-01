package com.example.bartek.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Bartek on 2016-10-29.
 */

public class Music extends Service implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private boolean isRunning = false;
    public MediaPlayer player;

    @Override
    public void onCreate()
    {
        super.onCreate();

        setMusicOptions(this, true, 100, 100, R.raw.main_menu);

        SharedPreferences musicPreference = PreferenceManager.getDefaultSharedPreferences(this);
        musicPreference.registerOnSharedPreferenceChangeListener(this);
    }

    private void setMusicOptions(Context context,
                                 boolean isLooped,
                                 int lVolume,
                                 int rVolume,
                                 int soundFile)
    {
        player = MediaPlayer.create(context, soundFile);
        player.setVolume(lVolume, rVolume);
        player.setLooping(isLooped);
    }

    @Override
    public IBinder onBind(Intent bind)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        player.stop();
        player.release();
        isRunning = false;
        Log.d("Music::onDestroy()", String.valueOf(isRunning));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try
        {
            player.start();
            isRunning = true;
            Log.d("Music::onStartCommand()", String.valueOf(isRunning));
        } catch (Exception e)
        {
            player.stop();
            isRunning = false;
            Log.d("Music::onStartCommand()", String.valueOf(isRunning));
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        player.stop();
        isRunning = false;
        Log.d("Music::onLowMemory()", String.valueOf(isRunning));
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("Music::onSharedPreferenceChanged()", String.valueOf(isRunning));
        if (key.equals("music_settings"))
        {
            if (isRunning){
                player.stop();
                isRunning = false;
                Log.d("Music::onSharedPreferenceChanged()", String.valueOf(isRunning));
            }
            else {
                player.start();
                isRunning = true;
                Log.d("Music::onSharedPreferenceChanged()", String.valueOf(isRunning));
            }
        }
    }
}
