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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try
        {
            player.start();
        } catch (Exception e)
        {
            player.stop();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        player.stop();
    }



    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }
}
