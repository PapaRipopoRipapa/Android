package com.example.bartek.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by Bartek on 2016-10-29.
 */

public class Music extends Service
{
    public static boolean isRunning = false;
    public MediaPlayer player;

    @Override
    public void onCreate()
    {
        super.onCreate();

        setMusicOptions(this, true, 100, 100, R.raw.main_menu);
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try
        {
            player.start();
            isRunning = true;
        } catch (Exception e)
        {
            isRunning = false;
            player.stop();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        player.stop();
        isRunning = false;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    public void onStop()
    {

    }
}
