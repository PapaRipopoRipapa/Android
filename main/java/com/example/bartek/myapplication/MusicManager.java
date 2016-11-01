package com.example.bartek.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.preference.PreferenceManager;

/**
 * Created by Bartek on 2016-10-29.
 */

public class MusicManager
        extends Service
        implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private boolean isRunning = false;
    private boolean isMusicEnabled;
    private MediaPlayer player;

    @Override
    public void onCreate()
    {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.main_menu);
        player.setVolume(100, 100);
        player.setLooping(true);

        SharedPreferences musicPreference = PreferenceManager.getDefaultSharedPreferences(this);
        musicPreference.registerOnSharedPreferenceChangeListener(this);
        isMusicEnabled = musicPreference.getBoolean("music_settings", true);
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (isMusicEnabled)
        {
            start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        pause();
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("music_settings"))
        {
            updateMusicState();
        }
    }

    private void updateMusicState()
    {
        if(isRunning)
            pause();
        else
            start();
    }

    private void pause()
    {
        player.pause();
        isRunning = false;
    }

    private void start()
    {
        try {
            player.start();
            isRunning = true;
        } catch (Exception e){
            System.out.println("MyApplication::MusicManager can't start music. Exception: "
                    + e.getMessage());
        }
    }
}
